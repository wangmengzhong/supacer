package com.wmz.auth.service.impl;

import org.springframework.util.StringUtils;

import com.wmz.auth.util.BeanTools;
import com.wmz.common.dao.BaseMapper;
import com.wmz.common.service.BaseService;
import com.wmz.common.service.impl.CommonBaseServiceImpl;

public class BaseServiceImpl extends CommonBaseServiceImpl implements BaseService {

	@SuppressWarnings({ "unchecked" })
	public <T> BaseMapper<T> getBaseMapper(Class<T> cls) {
		String beanName = StringUtils.uncapitalize(cls.getSimpleName()) + "Mapper";
		BaseMapper<T> baseMapper = (BaseMapper<T>) BeanTools.getBean(beanName);
		return baseMapper;
	}

}
