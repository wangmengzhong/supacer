package com.wmz.auth.dao;

import java.util.List;
import java.util.Map;

import com.wmz.common.dao.BaseMapper;
import com.wmz.common.entity.User;


public interface UserMapper extends BaseMapper<User>{
	
	List<User> getAll();
	
	User getOne(Long id);

	void delete(Long id);
	
	List userLogin(Map map);

}