package com.biz.sec.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailSendService {


	
	private final JavaMailSender javaMailSender;
	
	public MailSendService(@Qualifier("naverMailHander") JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}


	public void sendMail() {
		
		String from_email = "sagaro7@naver.com";
		String to_email = "jewon513@gmail.com";
		
		String subject = "메일보내기 테스트";
		String content = "반갑습니다";
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mHelper;
		
		mHelper = new MimeMessageHelper(message, "UTF-8");
		try {
			mHelper.setFrom(from_email);
			mHelper.setTo(to_email);
			mHelper.setSubject(subject);
			mHelper.setText(content,true); // true : 메일 본문에 html 효과주기 태그가 들어가있다면
			
			javaMailSender.send(message);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
}
