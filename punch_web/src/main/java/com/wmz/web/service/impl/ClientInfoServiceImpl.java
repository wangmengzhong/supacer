package com.wmz.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmz.web.dao.ClientInfoMapper;
import com.wmz.web.service.ClientInfoService;

@Service
public class ClientInfoServiceImpl extends BaseServiceImpl implements ClientInfoService {
	@Autowired
	private ClientInfoMapper clientInfoMapper;



}
