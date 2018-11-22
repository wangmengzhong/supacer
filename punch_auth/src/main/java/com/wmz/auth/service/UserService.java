package com.wmz.auth.service;

import java.util.List;

import com.wmz.common.service.BaseService;

public interface UserService extends BaseService{
	
	List userLogin(String name,String password);
	
	public List getAll();
	
}
