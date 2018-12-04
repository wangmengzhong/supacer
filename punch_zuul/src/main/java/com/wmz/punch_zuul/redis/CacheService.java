package com.wmz.punch_zuul.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.wmz.common.service.impl.BaseCacheServiceImpl;

@Component
public class CacheService extends BaseCacheServiceImpl{
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	@SuppressWarnings("rawtypes")
	@Override
	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}
	
}
