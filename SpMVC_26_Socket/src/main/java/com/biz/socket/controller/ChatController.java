package com.biz.socket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.biz.socket.domain.MessageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ChatController extends TextWebSocketHandler {

	List<WebSocketSession> sessionList;
	Map<String,MessageVO> messageMap;
	
	public ChatController() {
		sessionList = new ArrayList<>();
		messageMap = new HashMap<String, MessageVO>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		sessionList.add(session);
		
		MessageVO mVO = MessageVO.builder().webSocketSession(session).build();
		messageMap.put(session.getId(), mVO);
		
		
	}
	

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
		
		sessionList.remove(session);
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();
		
		String user[] = message.getPayload().split(":");
		if(user.length > 1) {
			if(user[0].equalsIgnoreCase("username")) {
				MessageVO mVO = messageMap.get(session.getId());
				mVO.setUserName(user[1]);
				
				
				map.put("msg","userName");
				map.put("userName", mVO.getUserName());
				
				session.sendMessage(new TextMessage(objectMapper.writeValueAsString(map)));
				return;
			}
		}
		
		if(message.getPayload().equalsIgnoreCase("getUserList")) {
			
			String userList = objectMapper.writeValueAsString(messageMap);
			map.put("msg","userList");
			map.put("userList",userList);
			
			String userListMap = objectMapper.writeValueAsString(map);
			
			this.sendMessage(session, userListMap);
			
		}
		
		Gson gson = new Gson();
		
		MessageVO messageVO = gson.fromJson(message.getPayload(), MessageVO.class);
		String sendMessage = String.format("%s 로부터 : %s", messageVO.getUserName(),messageVO.getMessage());
		
//		TextMessage sendTextMessage = new TextMessage(sendMessage);
		
		// jackson bind의 클래스인 ObjectMapper를 사용하여
		// VO클래스를 Json 형 문자열로 바로 변환시키기.
		
		String jsonTextMessage =  objectMapper.writeValueAsString(messageVO);
		
		this.sendMessage(session, jsonTextMessage);
		
	}
	
	private void sendMessage(WebSocketSession session, String jsonTextMessage) throws IOException {
		
		
		TextMessage sendTextMessage = new TextMessage(jsonTextMessage);
		for (WebSocketSession webSocketSession : sessionList) {
			// 자신이 보낸 메시지는 다시 자신에게 보낼 필요는 없다.
			// 따라서 자신이 보낸 메세지를 제외하고 전송을 한다.
			
			if(!webSocketSession.getId().equals(session.getId())) {
				webSocketSession.sendMessage(sendTextMessage);
			}
			
		}
		
	}
	
	
	
}
