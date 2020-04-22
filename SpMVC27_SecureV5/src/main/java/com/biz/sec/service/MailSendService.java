package com.biz.sec.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.utils.PbeEncryptor;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailSendService {

	private final JavaMailSender javaMailSender;
	private final String from_email = "sagaro7@naver.com";

	public MailSendService(@Qualifier("naverMailHander") JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}

	public void sendMail() {
		
		String to_email = "jewon513@gmail.com";
		
		String subject = "메일보내기 테스트";
		String content = "반갑습니다";
		
		this.sendMail(to_email,subject,content);
		
	}
	
	public void sendMail(String to_email, String subject, String content) {


		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mHelper;

		mHelper = new MimeMessageHelper(message, "UTF-8");
		try {
			mHelper.setFrom(from_email);
			mHelper.setTo(to_email);
			mHelper.setSubject(subject);
			mHelper.setText(content, true); // true : 메일 본문에 html 효과주기 태그가 들어가있다면

			javaMailSender.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 회원가입된 사용자에게 인증 email을 전송
	 * 
	 * username을 암호화 시키고 email인증을 수행 할 수 있는 링크를 email본문에 작성하여 전송을 할 것이다.
	 * @param userVO
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
//	public boolean join_send(UserDetailsVO userVO) {
	public String join_send(UserDetailsVO userVO) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

		String userName = userVO.getUsername();
		String email = userVO.getEmail();
		String encUserName = PbeEncryptor.getEncrypt(userName);
		String encEmail = PbeEncryptor.getEncrypt(email);

		StringBuilder email_link = new StringBuilder();
		email_link.append("http://localhost:8090/sec/");
		email_link.append("join/emailok?username=");
		email_link.append(URLEncoder.encode(encUserName, "UTF-8"));
		email_link.append("&email=");
		email_link.append(URLEncoder.encode(encEmail, "UTF-8"));

		StringBuilder email_message = new StringBuilder();
		email_message.append("<h3>회원가입을 환영합니다.</h3><br/>");
		email_message.append("<p>회원가입절차를 마무리하려면 email 인증을 해야 합니다.<br/>");
		email_message.append("아래의 Email 인증 링크를 클릭하세요.</p><br/>");
		email_message.append("<p><a href='%s'>Email 인증</a></p>");

		String sendMessage = String.format(email_message.toString(), email_link);

		String to_email = email;
		String subject = "봄나라 회원인증 메일";
		
		this.sendMail(to_email, subject, sendMessage);
		
		return sendMessage;
	}

	
	/**
	 * 
	 * @since 2020-04-21
	 * 이메일 인증을 위한 token 정보를 email로 전송하기
	 * 
	 * 
	 * @param userVO 
	 * @param email_token
	 */
	public void email_auth(UserDetailsVO userVO, String email_token) {
		// TODO Auto-generated method stub
		
		StringBuilder email_content = new StringBuilder();
		
		email_content.append("<style>");
		
		email_content.append(".biz-tone{");
		
		email_content.append("boder: 1px solid blue;");
		email_content.append("background-color: green;");
		email_content.append("color: white;");
		email_content.append("font-weight: bold;");
		
		email_content.append("}");
		
		email_content.append("</style>");
		
		email_content.append("<h2>회원가입을 환영합니다</h2>");
		
		email_content.append("<p>다음의 인증 코드를 회원 가입 인증코드 란에 입력해주세요.</p>");
		
		email_content.append("<div class='biz-tokne'>");
		email_content.append(email_token);
		email_content.append("</div>");
		
		String subject = "봄나라 회원인증 코드";
		
		this.sendMail(userVO.getEmail(), subject, email_content.toString());
		
		
	}

}

