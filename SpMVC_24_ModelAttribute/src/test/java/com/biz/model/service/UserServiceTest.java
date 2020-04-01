package com.biz.model.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.biz.model.domain.UserVO;

/*
 * 	Test Driven Develop 테스트 주도 개발
 * 	1. 클래스와 필요한 메서드의 이름만 만들고
 *  2. 필요에 따라 매개변수와 리턴값만 설정한다.
 *  3. Test에서 매개변수를 주입했을때 return되는 값이 일치함을 테스트하는 코드를 작성 한 후
 *  4. 실제 클래스에서 가상의 일치하는 데이터를 만들고
 *  5. 데이터를 return 하도록 코드를 작성
 *  6. 그리고 테스트를 수행하여, 통과되도록 코드를 리팩토링하여
 *  7. 임시로 클래스를 완성한다.
 *  
 *  DAO에서 데이터를 가져온 후 ID에 따라 사용자 ID와 사용자 이름이 일치하는 테스트 코드를 작성해 둔 상태이다.
 *  1. DAO에 데이터를 저장할 때 test코드가 통과될 수 있는 데이터를 INSERT 한 후
 *  2. 다시한번 테스트를 수행하여 DAO와 연동되는 것을 계속해서 테스트 한다. 확인한다.
 *  
 * 
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Test
	public void c_getUserTest() {
		
		UserVO userVO = userService.getUser("admin");
		
		assertEquals(userVO.getUserId(), "admin");
		assertEquals(userVO.getUserName(), "홍길동");
		
		userVO = userService.getUser("guest");
		
		assertEquals(userVO.getUserId(), "guest");
		assertEquals(userVO.getUserName(), "성춘향");
		
		userVO = userService.getUser("dba");
		
		assertEquals(userVO.getUserId(), "dba");
		assertEquals(userVO.getUserName(), "이몽룡");
	
	}
	
	
	@Test
	public void b_insertTest() {
		
		UserVO userVO = UserVO.builder().userId("korea").password("12345").userName("대한민국").userRole("gov").build();
		
		
		int result = userService.insert(userVO);
		
		assertEquals(result, 1);
//		
		
		userVO = UserVO.builder().userId("admin").password("12345").userName("홍길동").userRole("admin").build();
		result = userService.insert(userVO);
		
		assertEquals(result, 1);
		
		userVO = UserVO.builder().userId("guest").password("12345").userName("성춘향").userRole("guest").build();
		result = userService.insert(userVO);
		
		assertEquals(result, 1);
		
		userVO = UserVO.builder().userId("dba").password("12345").userName("이몽룡").userRole("dba").build();
		result = userService.insert(userVO);
		
		assertEquals(result, 1);
		
	}
	
	@Test
	public void a_deleteTest() {
		
		int ret =userService.delete("admin");
		ret =userService.delete("guest");
		ret =userService.delete("dba");
		ret =userService.delete("korea");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
