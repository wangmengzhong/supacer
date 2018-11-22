package com.wmz.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BaseMapper<T> {

	public T selectByPrimaryKey(String sid);

	public void deleteByPrimaryKey(String sid);

	public List<T> find(Map<String, Object> paramMap);

	public List<T> find(Map<String, Object> paramMap, RowBounds rowBounds);

	public int insert(T entity);

	public int update(T entity);

	public void delete(T entity);

}