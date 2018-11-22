package com.wmz.blog.hystrix;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.wmz.blog.feignClient.AuthFeignClient;
@Component
public class AuthFeignFallback implements AuthFeignClient{

	@Override
	public Object getUsers(Map params) {
		// TODO Auto-generated method stub
		return "123";
	}

}
