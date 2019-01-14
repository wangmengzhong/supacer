package com.wmz.stream.controler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rest/pro", produces = "application/json;charset=UTF-8")
public class StreamControler {
	
	@Autowired
	public com.wmz.stream.Producer producer;
	
	@RequestMapping("/test")
	public Object loginOut(HttpServletRequest request) {
		
		producer.send();
		return 111;
	}
}
