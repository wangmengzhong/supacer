package com.wmz.web.hystrix;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.wmz.common.entity.Result;
import com.wmz.web.client.AuthClient;

@Component
public class AuthFallback implements AuthClient{

	@Override
	public Object getUsers(Map params) {
		// TODO Auto-generated method stub
		return "123";
	}

	@Override
	public Result userLogin(Map params) {
		// TODO Auto-generated method stub
		return new Result();
	}

	@Override
	public Result loginOut(Map params) {
		// TODO Auto-generated method stub
		return null;
	}

}
