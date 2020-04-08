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

/*
 * 	LifeCycle Method
 * 	어떤 동작이 수행되는 과정에서 자동으로 호출되는 method 들
 * 
 * 	LifeCycle Method를 잘 구현하므로써
 * 	별도 어떤 동작에 해당하는 복잡한코드를 만들지 않아도 충분한 효과를 발휘 할 수 있다.
 * 
 */
@Slf4j
@Component
public class ChatController extends TextWebSocketHandler {

	List<WebSocketSession> sessionList;
	Map<String, MessageVO> messageMap;

	public ChatController() {
		sessionList = new ArrayList<>();
		messageMap = new HashMap<String, MessageVO>();
	}

	/*
	 * 서버와 클라이언트가 서로 소켓으로 연결되었을때 호출되는 method session을 별도로 저장하거나 하는 일들을 구현한다.
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		sessionList.add(session);

		
		/*
		 *  최조 어떤 사용자가 접속을 하게 되면 사용제에대한 메세지 정보를 담을 변수를 초기화 한다.
		 */
		MessageVO mVO = MessageVO.builder().build();
		messageMap.put(session.getId(), mVO);

	}

	/*
	 * 클라이언트와 연결이 정상 또는 비정상적으로 끊어졌을때, 수행할 코드를 작성
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);

		messageMap.remove(session.getId());
		sessionList.remove(session);

	}

	/*
	 * 클라이언트에서 메세지를 보내면 메시지를 수신하고, 연산코드를 수행한 후 그 결과를 다시 클라이언트에게 보내는 코드를 작성
	 * 
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();

		// 여기서부터는 임의의 프로토콜을 선언
		// 전달받은 메세지에 command가 포함되어 있는가를 구분하는 코드
		String user[] = message.getPayload().split(":");
		
		// command가 username이면
		// 채팅어플에 접속했을때 최초 사용자 이름을 입력하면 사용자 이름과 session을 정보에 저장한 후
		// 다시 클라이언트에게 알려주는 부분
		if (user.length > 1) {
			if (user[0].equalsIgnoreCase("username")) {
				MessageVO mVO = messageMap.get(session.getId());
				mVO.setUserName(user[1]);

				map.put("msg", "userName");
				map.put("userName", mVO.getUserName());

				
				String userName = objectMapper.writeValueAsString(map);
				this.sendMeMessage(session, userName);
				
				return;

				// command가 GetUserList이면
				// 현재 접속자 정보를 모든 클라이언트에게 보내기
			} else if (user[0].equalsIgnoreCase("getUserList")) {

				String userList = objectMapper.writeValueAsString(messageMap);
				map.put("msg", "userList");
				map.put("userList", userList);

				String userListMap = objectMapper.writeValueAsString(map);

				log.debug("Chat 컨트롤러 : " + userListMap);
				
				this.sendAllMessage(session, userListMap);
				return;
			}

		}

		// 실제 채팅이 진행되어지는 과정에서 메시지를 전파하는 부분
		Gson gson = new Gson();

		MessageVO messageVO = gson.fromJson(message.getPayload(), MessageVO.class);

		// jackson bind의 클래스인 ObjectMapper를 사용하여
		// VO클래스를 Json 형 문자열로 바로 변환시키기.

		String jsonTextMessage =  objectMapper.writeValueAsString(messageVO);

		
		if(messageVO.getToUser().equalsIgnoreCase("ALL")) {
			// 나를 제외한 전체에게 메세지 보내기
			this.sendNotMeMessage(session, jsonTextMessage);			
		}else {
			// 전체가 아니면 전송된 session id값을 sessionList에서 조회하여 일치하는 값이 있으면 해당 접속자에게만 메시지 보내기
			for (WebSocketSession webSocketSession : sessionList) {
				if(webSocketSession.getId().equals(messageVO.getToUser())) {
					this.sendMeMessage(webSocketSession, jsonTextMessage);
					break;
				}
			}
		}


	}



	// 요청한 접속자에게만 메시지 보내기
	private void sendMeMessage(WebSocketSession session, String jsonTextMessage) throws IOException {
	
		TextMessage sendMessage = new TextMessage(jsonTextMessage);
		
		session.sendMessage(sendMessage);
		
	}

	// 무조건 전체 접속자에게 메시지 보내기
	private void sendAllMessage(WebSocketSession session, String jsonTextMessage) throws IOException {

		TextMessage sendTextMessage = new TextMessage(jsonTextMessage);
		for (WebSocketSession webSocketSession : sessionList) {
			webSocketSession.sendMessage(sendTextMessage);
		}

	}

	// 나를 제외한 나머지 접속자에게 메시지 보내기
	private void sendNotMeMessage(WebSocketSession session, String jsonTextMessage) throws IOException {

		TextMessage sendTextMessage = new TextMessage(jsonTextMessage);
		for (WebSocketSession webSocketSession : sessionList) {
			// 자신이 보낸 메시지는 다시 자신에게 보낼 필요는 없다.
			// 따라서 자신이 보낸 메세지를 제외하고 전송을 한다.

			if (!webSocketSession.getId().equals(session.getId())) {
				webSocketSession.sendMessage(sendTextMessage);
			}

		}

	}

}
