package com.biz.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.biz.security.persistence.MemberDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String userid = authentication.getName();
		
		log.debug("로그인한 유저의 아이디 : " + userid);
		
		memberDao.update(userid);
		
		response.sendRedirect("/security/");
		
		return;
		
		// TODO Auto-generated method stub
		
	}

}
