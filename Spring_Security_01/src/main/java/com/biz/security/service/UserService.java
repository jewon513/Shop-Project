package com.biz.security.service;

import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.security.domain.AuthVO;
import com.biz.security.domain.MemberVO;
import com.biz.security.persistence.AuthDao;
import com.biz.security.persistence.MemberDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final MemberDao memberDao;
	private final AuthDao authDao;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Transactional(rollbackFor = SQLException.class)
	public int join(MemberVO memberVO) {
		
		memberVO.setUserpw(bCryptPasswordEncoder.encode(memberVO.getUserpw()));
		
		int ret = memberDao.insert(memberVO);
		
		
		
		if(ret > 0) {
			
			AuthVO authVO = AuthVO.builder().userid(memberVO.getUserid()).auth("ROLE_MEMBER").build();
			
			ret = authDao.insert(authVO);
			
		}
		
		return ret;
	}
	
	public int updateUserDate(String userid) {
		
		int ret = memberDao.update(userid);
		
		return ret;
		
	}

}
