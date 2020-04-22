package com.biz.sec.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.sec.domain.AuthorityVO;
import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.persistance.AuthoritiesDao;
import com.biz.sec.persistance.UserDao;
import com.biz.sec.utils.PbeEncryptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	// @Autowirted
	private final PasswordEncoder passwordEncoder;

	private final UserDao userDao;

	private final AuthoritiesDao authDao;
	
	private final MailSendService mailSendService;

	public UserService(PasswordEncoder passwordEncoder, UserDao userDao, AuthoritiesDao authDao, MailSendService mailSendService) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
		this.authDao = authDao;
		this.mailSendService = mailSendService;

		String create_user_table = " CREATE TABLE IF NOT EXISTS tbl_users (" + "  id bigint PRIMARY KEY AUTO_INCREMENT,"
				+ "  user_name varchar(50) UNIQUE," + "  user_pass varchar(125)," + "  enabled boolean default true,"
				+ "  email varchar(50)," + "  phone varchar(20)," + "  address varchar(125) " + " )";

		String create_auth_table = "CREATE TABLE IF NOT EXISTS authorities ("
				+ "  id bigint PRIMARY KEY AUTO_INCREMENT," + "  username varchar(50) ," + "  authority varchar(50) "
				+ " )";

		userDao.create_table(create_user_table);
		userDao.create_table(create_auth_table);

	}

	/**
	 * @since 2020-04-09
	 * @author callor
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 *         회원가입을 신청하면 비밀번호는 암호화하고 아이디와 비번을 DB insert 수행
	 * 
	 * 
	 */

	@Transactional
	public int insert(String username, String password) {

		// 회원가입 form에서 전달받은 password 값을 암호화 시키는 과정
		String encPassword = passwordEncoder.encode(password);
		UserDetailsVO userVO = UserDetailsVO.builder().username(username).password(encPassword).build();

		int ret = userDao.insert(userVO);

		List<AuthorityVO> authList = new ArrayList<AuthorityVO>();

		AuthorityVO auth = AuthorityVO.builder().username(username).authority("ROLE_USER").build();

		authList.add(auth);

		auth.setAuthority("USER");

		authList.add(auth);

		authDao.insert(authList);

		return ret;

	}

	/**
	 * 
	 * @since 2020-04-20
	 * @author jewon 새로작성된 회원가입에서 회원가입을 처리할 method
	 * 
	 *         userVO를 파라메터로 받아서 enabled를 false로 처리하고 role 정보는 업데이트 하지 않는 것으로 처리해
	 *         놓는다.
	 * 
	 *         이후 email 인증이 오면
	 * 
	 *         enabled와 role 정보를 설정하도록 한다.
	 * 
	 * @param userVO
	 * @return
	 */
	@Transactional
//	public int insert(UserDetailsVO userVO) {
	public int insert(UserDetailsVO userVO) {
		// TODO Auto-generated method stub

		userVO.setEnabled(false);
		userVO.setAuthorities(null);

		// 회원가입 form에서 전달받은 password 값을 암호화 시키는 과정
		String encPassword = passwordEncoder.encode(userVO.getPassword());
		userVO.setPassword(encPassword);
		
//		boolean bRet = mailSendService.join_send(userVO);
		String sRet = "";
		try {
			sRet = mailSendService.join_send(userVO);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int ret = userDao.insert(userVO);
		return ret;
		
	}

	public boolean isExistsUserName(String username) {

		UserDetailsVO userVO = userDao.findByUserName(username);
		// 이미 DB에 회원정보(username)이 저장되어 있다.
		if (userVO != null && userVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		return false;

	}

	public UserDetailsVO findById(long id) {
		// TODO Auto-generated method stub

		UserDetailsVO userVO = userDao.findById(id);

		return userVO;
	}

	public boolean checkPassword(String password) {
		// TODO Auto-generated method stub

		UserDetailsVO userVO = (UserDetailsVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return passwordEncoder.matches(password, userVO.getPassword());
	}

	public int update(UserDetailsVO userVO, String[] authList) {

		int ret = userDao.update(userVO);

		if (ret > 0) {

			List<AuthorityVO> authCollection = new ArrayList<AuthorityVO>();
			for (String auth : authList) {
				if (!auth.isEmpty()) {
					AuthorityVO authVO = AuthorityVO.builder().username(userVO.getUsername()).authority(auth).build();
					authCollection.add(authVO);
				}
			}

			authDao.delete(userVO.getUsername());
			authDao.insert(authCollection);
		}

		return ret;

	}

	@Transactional
	public int update(UserDetailsVO userVO) {
		// TODO Auto-generated method stub

		Authentication oldAuthentication = SecurityContextHolder.getContext().getAuthentication();

		UserDetailsVO oldUserVO = (UserDetailsVO) oldAuthentication.getPrincipal();

		oldUserVO.setEmail(userVO.getEmail());
		oldUserVO.setPhone(userVO.getPhone());
		oldUserVO.setAddress(userVO.getAddress());

		int ret = userDao.update(userVO);

		if (ret > 0) {
			Authentication newAuthentication = new UsernamePasswordAuthenticationToken(oldUserVO,
					oldAuthentication.getCredentials(), oldAuthentication.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(newAuthentication);
		}

		return ret;
	}

	private Collection<GrantedAuthority> getAuthorities(String[] authList) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String auth : authList) {

			if (!auth.isEmpty()) {
				SimpleGrantedAuthority sAuth = new SimpleGrantedAuthority(auth);
				authorities.add(sAuth);
			}

		}

		log.debug(authorities.toString());

		return authorities;

	}

	@Transactional
	public List<UserDetailsVO> selectAll() {
		// TODO Auto-generated method stub

		return userDao.selectAll();

	}

	public UserDetailsVO findByUserName(String username) {
		// TODO Auto-generated method stub

		return userDao.findByUserName(username);

	}

	@Transactional
	public boolean emailok(String username, String email) {
		// TODO Auto-generated method stub
		
		String strUserName = PbeEncryptor.getDecrypt(username);
		UserDetailsVO userVO = userDao.findByUserName(strUserName);
		
		String strEmail = PbeEncryptor.getDecrypt(email);
		if(strEmail.equalsIgnoreCase(userVO.getEmail())) {
			userVO.setEnabled(true);
			
			userDao.update(userVO);
			
			List<AuthorityVO> authList = new ArrayList<AuthorityVO>();

			AuthorityVO auth = AuthorityVO.builder().username(strUserName).authority("ROLE_USER").build();
			authList.add(auth);

			auth = AuthorityVO.builder().username(strUserName).authority("USER").build();
			authList.add(auth);

			authDao.insert(authList);
			
			return true;
			
		}
		
		return false;
		
	}

	/**
	 * @since 2020-04-21
	 * 회원 정보를 받아서 DB에 저장하고 회원 정보를 활성화 할 수 있도록 하기 위해 인증 정보를 생성한 후
	 * Controller로 Return 한다.
	 * 
	 * @param userVO
	 * @return
	 */
	public String insert_getToken(UserDetailsVO userVO) {
		// DB에 저장
		
		String encPassword = passwordEncoder.encode(userVO.getPassword());
		userVO.setPassword(encPassword);
		userVO.setEnabled(false);
		userDao.insert(userVO);
		
		String email_token = UUID.randomUUID().toString().split("-")[0].toUpperCase();
		
		
		log.debug("EMAIL-TOKEN : " + email_token);
		String enc_email_token = PbeEncryptor.getEncrypt(email_token);
		
		// Email 보내기
		mailSendService.email_auth(userVO, email_token);
		
		
		return enc_email_token ;
	}

	public boolean email_token_ok(String username, String secret_key, String secret_value) {
		// TODO Auto-generated method stub
		
		boolean bKey = PbeEncryptor.getDecrypt(secret_key).equals(secret_value);
		
		if(bKey) {

			String strUserName = PbeEncryptor.getDecrypt(username);
			
			UserDetailsVO userVO = userDao.findByUserName(strUserName);
			
			userVO.setEnabled(true);
			userDao.update(userVO);
			authDao.delete(userVO.getUsername());
			
			List<AuthorityVO> authList = new ArrayList<AuthorityVO>();

			AuthorityVO auth = AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build();
			authList.add(auth);

			auth = AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build();
			authList.add(auth);

			authDao.insert(authList);
			
			
			
		}
		
		
		
		return bKey;
		
		
	}

}
