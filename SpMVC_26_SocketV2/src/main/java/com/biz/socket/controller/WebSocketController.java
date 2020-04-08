package com.biz.socket.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;


/*
 * STOMP를 지원하는 기능을 사용할 수 있도록 만들어져 있는 기본 클래스(TextWebSocketHandler)
 */
@Slf4j
@Component
public class WebSocketController extends TextWebSocketHandler{

	// WebSocketSession
	// socket으로 서버에 접속할때 접속되는 클라이언트들을 관리하기 위한 session
	List<WebSocketSession> sessionList;
	
	public WebSocketController() {
		sessionList = new ArrayList<WebSocketSession>();
	}

	// 클라이언트가 웹소켓을 통해서 접속을 시도할때 처음 실행될 method 
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		
		// 접속된 클라이언트의 정보를 sessionList에 추가한다.
		sessionList.add(session);
		log.debug("접속된 클라이언트 ID: {}", session.getId());
		
	}

	// 클라이언트가 메시지를 보내면 메시지를 수신하는 컨트롤러 method
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);
		
		log.debug("{} 클라이언트가 보낸 메세지 : {}",session.getId(),message.getPayload());
		
		for(WebSocketSession ws : sessionList) {
			ws.sendMessage(message);
		}
		
	}
	
	
	
	
	
	
}
