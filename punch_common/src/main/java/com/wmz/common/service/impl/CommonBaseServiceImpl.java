package com.wmz.common.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.wmz.common.dao.BaseMapper;
import com.wmz.common.service.BaseService;

public abstract class CommonBaseServiceImpl implements BaseService {

	public abstract <T> BaseMapper<T> getBaseMapper(Class<T> cls);
	
	@Override
	public <T> T selectByPrimaryKey(Class<T> cls, String id) {
		return getBaseMapper(cls).selectByPrimaryKey(id);
	}

	@Override
	public <T> List<T> find(Class<T> cls, Map<String, Object> params) {
		System.out.println("无缓存的时候调用这里---数据库查询");
		// 查询所有
		return getBaseMapper(cls).find(params);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public <T> List<T> findByPage(Class<T> cls, Map<String, Object> params) {
		System.out.println("无缓存的时候调用这里---数据库查询---分页查询");
		RowBounds rowBounds=new RowBounds(Integer.parseInt(params.get("page")+""),Integer.parseInt(params.get("limit")+"")); 
		List list=getBaseMapper(cls).find(params,rowBounds);
		
		return list;
	}

	@Override
	public <T> void insert(Class<T> cls, T entity) {
		// 添加
		getBaseMapper(cls).insert(entity);
	}

	@Override
	public <T> void update(Class<T> cls, T entity) {
		// 更新
		int r = getBaseMapper(cls).update(entity);
		if (r == 0) {
			throw new RuntimeException();
		}
	}

	@Override
	public <T> void deleteByPK(Class<T> cls, String id) {
		getBaseMapper(cls).deleteByPrimaryKey(id);
	}

}
