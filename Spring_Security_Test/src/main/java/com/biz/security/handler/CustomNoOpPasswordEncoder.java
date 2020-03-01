package com.biz.security.handler;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder {@Override
	public String encode(CharSequence rawPassword) {

		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {

		
		return rawPassword.toString().equals(encodedPassword);
		
		
	}

}
