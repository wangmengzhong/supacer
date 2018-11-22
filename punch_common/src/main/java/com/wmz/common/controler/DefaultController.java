package com.wmz.common.controler;

import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.github.pagehelper.PageInfo;
import com.wmz.common.service.BaseService;
import com.wmz.common.controler.BaseController;

public abstract class DefaultController<T> extends BaseController<T> {

	public abstract BaseService getBaseService();

	/**
	 * 通用根据id查询出一条数据
	 * @param id
	 * @return Object
	 */
	public Object findById(String id) {
		return getBaseService().selectByPrimaryKey(entityClass, id);
	}

	/**
	 * 通用分页查询
	 * @param filter
	 * @param rowBound
	 * @return pageInfo对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo findByPage(Map<String, Object> filter) {
		return new PageInfo(getBaseService().findByPage(entityClass, filter));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo find(Map<String, Object> filter) {
		return new PageInfo(getBaseService().find(entityClass, filter));
	}

	// 通用添加
	public Object add(T obj) {
		getBaseService().insert(entityClass, obj);
		return obj;
	}

	// 通用删除
	public void delete(String id) {
		getBaseService().deleteByPK(entityClass, id);
	}

	// 通用修改
	public void update(T obj) {
		getBaseService().update(entityClass, obj);
	}

}
