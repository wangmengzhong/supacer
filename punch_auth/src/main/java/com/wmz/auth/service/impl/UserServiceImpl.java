package com.wmz.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmz.auth.dao.UserMapper;
import com.wmz.common.entity.User;
import com.wmz.auth.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List getAll() {
		List<User> users = userMapper.getAll();
		return users;
	}

	@Override
	public List userLogin(String name, String password) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userName", name);
		params.put("passWord", password);
		List list=userMapper.userLogin(params);
		return list;
	}

}
