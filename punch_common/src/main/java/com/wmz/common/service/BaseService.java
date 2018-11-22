package com.wmz.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;


public interface BaseService {
	
	public <T> T selectByPrimaryKey(Class<T> cls, String id);
	
	public <T> List<T> findByPage(Class<T> cls, Map<String, Object> params);
	
	public <T> List<T> find(Class<T> cls, Map<String, Object> params);
	
	public <T> void insert(Class<T> cls, T entity);

	public <T> void update(Class<T> cls, T entity);

	public <T> void deleteByPK(Class<T> cls, String id);
	
}
