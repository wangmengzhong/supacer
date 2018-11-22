package com.wmz.web.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

@Component
public class SystemWebSocketHandler implements WebSocketHandler {

	public static final ArrayList<WebSocketSession> users;

	static {
		users = new ArrayList<WebSocketSession>();
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		System.out.println("--afterConnectionClosed--");
		
		users.remove(session);
	}

	@SuppressWarnings("unused")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		System.out.println("--afterConnectionEstablished--");
		
		for (WebSocketSession user : users) {
			System.out.println(user.getId());
		}
		System.out.println("Server:cennected OK!");
		users.add(session);

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage message) throws Exception {
		
		System.out.println("--handleMessage--");
		
		Gson gson=new Gson();
		
		TextMessage tm = new TextMessage(message.getPayload() + "");
		
		Map<String, String> map = gson.fromJson(message.getPayload().toString(), HashMap.class);  
		
		System.out.println(map);
		System.out.println("users:"+users);
		
		session.sendMessage(tm);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
		
		System.out.println("--handleTransportError--");
		
		if (session.isOpen()) {
			session.close();
		}
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 * 
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.isOpen()) {
				try {
					user.sendMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 发送消息给指定用户
	 * 
	 * @param userId
	 * @param message
	 */
	public void sendMessageToUser(String userId, TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.getAttributes().get("userId").equals(userId)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
