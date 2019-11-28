package com.wmz.punchSell.service.impl;

import org.springframework.stereotype.Service;

import com.wmz.common.service.impl.ServiceImpl;
import com.wmz.punchSell.dao.ProductDao;
import com.wmz.punchSell.domain.ProductInfo;
import com.wmz.punchSell.service.IProductService;

/**
* 作者:步程
* 创建时间:2018-05-24
**/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao,ProductInfo> implements IProductService{


}