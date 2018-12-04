package com.wmz.punch_zuul.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

@Component
public class RouteFilter extends ZuulFilter{
	@Override
	public Object run() {
		System.out.println("---- RouteFilter run-------");
        return null;
	}
 
	@Override
	public boolean shouldFilter() {
		return true;// 是否执行该过滤器，此处为true，说明需要过滤
	}
 
	@Override
	public int filterOrder() {
		return 2;// 数字越大，优先级越低
	}
 
	@Override
	public String filterType() {
		return "route";
	}

}
