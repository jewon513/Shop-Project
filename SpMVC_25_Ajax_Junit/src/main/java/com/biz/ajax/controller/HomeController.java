package com.biz.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.ajax.domain.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		UserVO userVO = new UserVO();
		
		
		/*
		 * 	userVO데이터를 생성하고 (Service로 부터 가져오고)
		 *  model userVO를 싣고
		 *  return "home" (home.jsp 호출하여)
		 *  userVO와 home.jsp를 rendering 하여
		 *  web client로 전송한다(html 코드로 변환되어 전송)
		 */
		model.addAttribute("userVO",userVO.SampleVO());
		
		return "home";
	}
	
	
	
}
