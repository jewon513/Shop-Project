package com.biz.model.service;

import org.springframework.stereotype.Service;

import com.biz.model.dao.UserDao;
import com.biz.model.domain.UserVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserDao userDao;
	
	public UserVO getUser(String userId) {
//		UserVO userVO = UserVO.builder().userId("admin").password("12345").userName("홍길동").userRole("admin").build();
		
		UserVO userVO = userDao.findByUserId("admin");
	
		if(userId.equals("admin")) {
			return userVO;
		}else if(userId.equals("guest")) {
			
//			userVO.setUserId("guest");
//			userVO.setUserName("성춘향");
			
			userVO = userDao.findByUserId("guest");
			
			
		}else if(userId.equals("dba")) {
			
//			userVO.setUserId("dba");
//			userVO.setUserName("이몽룡");
			
			userVO = userDao.findByUserId("dba");
			
		}else {
			userVO = null;
		}
		
		return userVO;
	}
	
	
	/*
	 * insert method는 UserVO의 데이터를 받아서 insert를 수행하는 후
	 * 결과를 return 하도록 되어있다.
	 * 이떄 return 하는 값은 데이터가 1개 이므로 정수 1이(0 보다 큰 값) 된다.
	 * 이러한 테스트 코드를 만들기 위해서 일단 가상으로 return 1의 코드를 추가한다.
	 */
	public int insert(UserVO userVO) {
		
		return userDao.insert(userVO);
		
	}


	public int delete(String userId) {

		return userDao.delete(userId);
	}
	
}
