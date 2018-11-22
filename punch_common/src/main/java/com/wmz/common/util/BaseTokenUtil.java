package com.wmz.common.util;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.google.gson.Gson;
import com.wmz.common.entity.User;
import com.wmz.common.safe.rsa.SecurityUtils;

public abstract class BaseTokenUtil {
	
	Gson gson=new Gson();

	@SuppressWarnings("rawtypes")
	public abstract RedisTemplate getRedisTemplate();

	/**
	 * 获取当前登录用户的信息
	 * 
	 * @param sessionId
	 * @return
	 */
	public User getUserInfo(String sessionId) {
		System.out.println("get:"+sessionId);
		Object result = null;
		ValueOperations<Serializable, Object> operations = getRedisTemplate().opsForValue();
		result = operations.get(sessionId);
		if(result!=null){
			User user=gson.fromJson(result.toString(), User.class);
			return user;
		}else{
			return null;
		}
	}

	/**
	 * 设置当前登录用户的信息
	 * @param key sessionId　
	 * @param value user
	 * @param expireTime 
	 * @return key
	 */
	public String setUserInfo(Object value, Long expireTime) {
		//生成sessionId
		String key=UUID.randomUUID().toString().replace("-", "");  
		//rsa加密
		String mi=SecurityUtils.encrypt(key);
		System.out.println("set:"+key);
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = getRedisTemplate().opsForValue();
			operations.set(key, value);
			getRedisTemplate().expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

}
