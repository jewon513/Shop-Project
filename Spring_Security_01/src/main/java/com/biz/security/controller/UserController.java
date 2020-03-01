package com.biz.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.security.domain.MemberVO;
import com.biz.security.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

	
	private final UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(String error, String logout, Model model) {
		
		if (error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}
		
		if (logout != null) {
			model.addAttribute("logout", "Logout Complete");
		}
		
		return "login";
	}
	
	@RequestMapping(value = "join", method = RequestMethod.GET)
	public String join() {
		
		return "join";
	}
	
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String join(MemberVO memberVO) {
		
		log.info("회원가입 정보 : " + memberVO.toString());

		int ret = userService.join(memberVO);
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "accessError")
	public String accessDenied(Authentication auth, Model model) {
		
		log.info("access Denied :" + auth);
		
		model.addAttribute("msg", "권한이 없습니다.");
		
		return "login";
		
	}
	
	
	
}




