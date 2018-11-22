package com.wmz.web.service.impl;

import org.springframework.util.StringUtils;

import com.wmz.common.dao.BaseMapper;
import com.wmz.common.service.BaseService;
import com.wmz.common.service.impl.CommonBaseServiceImpl;
import com.wmz.web.util.BeanTools;

public class BaseServiceImpl extends CommonBaseServiceImpl implements BaseService {

	@SuppressWarnings({ "unchecked" })
	public <T> BaseMapper<T> getBaseMapper(Class<T> cls) {
		String beanName = StringUtils.uncapitalize(cls.getSimpleName()) + "Mapper";
		BaseMapper<T> baseMapper = (BaseMapper<T>) BeanTools.getBean(beanName);
		return baseMapper;
	}

}
