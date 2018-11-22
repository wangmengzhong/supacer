package com.wmz.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wmz.common.entity.User;

public class SessionUtil {
	
	/**
	 * session里面存储当前登录的用户信息的key
	 */
	public static final String SESSION_KEY_LOGIN_USERINFO = "user";

	public static User getNowUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SESSION_KEY_LOGIN_USERINFO);
		if(user!=null){
			return user;
		}else{
			return null;
		}
	}

	public static String getNowUserName(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SESSION_KEY_LOGIN_USERINFO);
		if(user!=null){
			String userName = user.getUserName();
			return userName;
		}else{
			return null;
		}
	}

	public static String getNowUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SESSION_KEY_LOGIN_USERINFO);
		if(user!=null){
			String userId = user.getId().toString();
			return userId;
		}else{
			return null;
		}
	}

}
