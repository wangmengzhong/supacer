package com.wmz.punch_zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PostFilter extends ZuulFilter{
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
 
		System.out.println("----- PostFilter run --------\n");
		
		return null;
	}
 
	@Override
	public boolean shouldFilter() {
		return true;
	}
 
	@Override
	public int filterOrder() {
		return 0;
	}
 
	@Override
	public String filterType() {
		return "post";// 在请求被处理之后，会进入该过滤器
	}

}
