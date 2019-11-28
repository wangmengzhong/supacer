package com.wmz.punchSell.service.impl;

import org.springframework.stereotype.Service;

import com.wmz.common.service.impl.ServiceImpl;
import com.wmz.punchSell.dao.ProductCategoryDao;
import com.wmz.punchSell.domain.ProductCategory;
import com.wmz.punchSell.service.IProductCategoryService;

@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryDao,ProductCategory> implements IProductCategoryService{

}
