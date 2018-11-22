package com.wmz.web.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;


@Component
public class MyHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,ServerHttpResponse response, WebSocketHandler handler,
			Map<String, Object> map) throws Exception {
		
		if (request instanceof ServletServerHttpRequest) {
			
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			System.out.println("注册成功！");
			if (session!=null) {
				/*User user = (User) session.getAttribute("user");
				if(user!=null){
					map.put("userId", user.getId());
				}else{
					System.err.println("未登录！");
					return false;
				}*/
				
			}
		}
		
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request,ServerHttpResponse response, WebSocketHandler wsHandler,Exception ex) {
		
	}
	
}
