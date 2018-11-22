package com.wmz.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.wmz.common.util.BaseTokenUtil;

@Component
public class TokenUtil extends BaseTokenUtil{
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@SuppressWarnings("rawtypes")
	@Override
	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

}
