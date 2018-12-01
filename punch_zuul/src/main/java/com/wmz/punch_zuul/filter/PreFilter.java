package com.wmz.punch_zuul.filter;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PreFilter extends ZuulFilter {
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println("------- PreFilter run-------");
        return null;
        /*String username = request.getParameter("password");
        if(null != username && username.equals("123456")) {
        	ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        }else{
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"password is not correct!\"}");
            ctx.set("isSuccess", false);
            return null;
        }*/
	}
 
	@Override
	public boolean shouldFilter() {
		//RequestContext ctx = RequestContext.getCurrentContext();
		//return (boolean) ctx.get("isSuccess");// 如果前一个过滤器的结果为true，则说明上一个过滤器成功了，需要进入当前的过滤，如果前一个过滤器的结果为false，则说明上一个过滤器没有成功，则无需进行下面的过滤动作了，直接跳过后面的所有过滤器并返回结果
		return true;
	}
 
	@Override
	public int filterOrder() {
		return 1; // 优先级设置为1
	}
 
	@Override
	public String filterType() {
		return "pre";
	}

}
