package com.wmz.punch_zuul.filter.router;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class RouteFilter extends ZuulFilter{
	@Override
	public Object run() {
		System.out.println("---- RouteFilter run-------");
        return null;
	}
 
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		Object isSuccess=ctx.get("isSuccess");
		
		System.out.println("---- RouteFilter shouldFilter---- return："+(boolean) isSuccess);
		return (boolean) isSuccess;// 如果前一个过滤器的结果为true，则说明上一个过滤器成功了，需要进入当前的过滤，如果前一个过滤器的结果为false，则说明上一个过滤器没有成功，则无需进行下面的过滤动作了，直接跳过后面的所有过滤器并返回结果
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
