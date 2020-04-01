package com.biz.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.biz.model.domain.UserVO;
import com.biz.model.service.UserService;

public class UserController {

	@Autowired
	protected UserService userService;
	
	/*
	 *  클래스의 method에 @ModelAttribute를 설정하고
	 *  Qualifier(객체 이름 지정하기를) 지정해주면 이 method는 컨트롤러의 다른 method가 실행되기 전에 코드를 실행하여
	 *  객체를 model에 담아주는 일을 수행한다.
	 *  
	 *  컨트롤러에서
	 *  view에 rendering을 위한 데이터를 내려보낼때
	 *  필요할때마다 model을 생성하고
	 *  model.addattribute() 를 사용하여 데이터를 보내는데,
	 *  공통적으로 view에서 사용해야할 데이터가 있을 때 
	 *  method에 modelattribute를 설정해 두면 view에서 해당 데이터를 가져다가 rendering 용으로 사용이 가능하다.
	 *  
	 */
	
	@ModelAttribute("userVO")
	public UserVO getUser() {
		
		return userService.getUser("admin");
		
		
	}
	
	
}
