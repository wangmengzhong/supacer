package com.wmz.web.hystrix;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmz.web.client.BlogClient;

@Component
public class BlogFallback implements BlogClient{

	@Override
	public Object getBlogList(Map params) {
		// TODO Auto-generated method stub
		return "getBlogList fallback";
	}

	@Override
	public Object getBlogInf(Map params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addBlog(@RequestParam Map<String, Object> map,Map params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateBlog(Map params) {
		// TODO Auto-generated method stub
		return null;
	}


}
