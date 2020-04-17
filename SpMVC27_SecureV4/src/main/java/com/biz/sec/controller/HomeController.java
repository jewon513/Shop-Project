package com.biz.sec.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.sec.domain.UserDetailsVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	
	/*
	 * security mapping을 annotation을 사용하여 설정
	 * @Secure(value ={문자열 배열 형식으로 값을 설정})
	 */
//	@Secured(value = {"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value = "auth", method = RequestMethod.GET)
	public String auth(Model model) {
		return "auth/auth-view";
	}
	
	
	/**
	 * Controller의 method에 HttpsServletRequest 클래스로 부터 인증(로그인한) 정보를 추출하여 세부 항목을 보는 방법
	 * 
	 * 
	 * @param id
	 * @param httpServletRequest
	 * @return
	 */
	@ResponseBody
	@Secured(value = {"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping(value = "auth/{id}", method = RequestMethod.GET)
	public Object auth(@PathVariable(value = "id") String id, HttpServletRequest httpServletRequest) {
		
		
		int intId = 0;
		try {
			intId = Integer.valueOf(id);
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
		Authentication auth = (Authentication) httpServletRequest.getUserPrincipal();
		
		
		
		
		switch (intId) {
		case 1:
			return auth.getDetails();
		case 2:
			return auth.getCredentials();
		case 3:
			return auth.getPrincipal();
		case 4:
			return auth.getAuthorities();
		case 5:
			return auth.getName();
		case 6:
			
			UserDetailsVO userVO = (UserDetailsVO) auth.getPrincipal();
			return userVO;
			
			
		default:
			return "Not Found";
		}
		
		
	}
	
	
}
