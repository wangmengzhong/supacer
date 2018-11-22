package com.wmz.blog.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.wmz.common.service.impl.BaseCacheServiceImpl;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
