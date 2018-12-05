package com.wmz.punch_zuul.filter.pre;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.wmz.common.entity.User;
import com.wmz.punch_zuul.filter.BodyReaderHttpServletRequestWrapper;
import com.wmz.punch_zuul.util.TokenUtil;

@Component
public class PreFilter extends ZuulFilter {
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        StringBuffer url = request.getRequestURL();
		url.append("#");
        if (isWhitURL(url.toString())) {
        	System.out.println("--- PreFilter run ------- 白名单 --- URL："+request.getRequestURL());
        	returnSuccess(ctx);
		} else {
			Map map;
			try {
				ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
				map = PreFilter.getBodyString(requestWrapper.getReader());
				if (map == null) {
					returnError(ctx);
				} else {
					String sessionId = map.get("sessionId") != null ? map.get("sessionId").toString() : null;
					if (sessionId == null) {
						returnError(ctx);
					} else {
						User user = tokenUtil.getUserInfo(sessionId);
						if (user != null) {
							System.out.println("--- PreFilter run ------- 获得session成功 --- URL："+request.getRequestURL());
							returnSuccess(ctx);
						} else {
							System.out.println("--- PreFilter run ------- 未获得session --- URL："+request.getRequestURL());
							returnError(ctx);
						}
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
        return null;
	}
 
	@Override
	public boolean shouldFilter() {
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
	
	/**
	 * 判断请求路径是否需要登录信息(不需要则返回true)
	 * 
	 * @param URL
	 * @return
	 */
	private boolean isWhitURL(String URL) {
		boolean flag = false;
		// 白名单
		List<String> list = new ArrayList<String>();

		list.add("rest/system/userLogin");// 登录
		list.add("rest/blog/uploadImages");
		list.add("rest/blog/getImage");
		list.add("webSocketServer");
		list.add("rest/blog/getBlog");
		list.add("/web/images");
		
		for (int i = 0; i < list.size(); i++) {
			String[] uriArray = URL.split(list.get(i));
			if (uriArray.length >= 2) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getBodyString(BufferedReader br) {
		Gson gson = new Gson();
		String inputLine;
		String str = "";
		try {
			while ((inputLine = br.readLine()) != null) {
				str += inputLine;
			}
			br.close();
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}

		Map<String, Object> map = gson.fromJson(str, Map.class);
		return map;
	}
	
	public static void returnSuccess(RequestContext ctx) {
		ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        ctx.set("isSuccess", true);
	}
	
	public static void returnError(RequestContext ctx) {
		ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(200);
        ctx.getResponse().setContentType("application/json;charset=UTF-8");
        ctx.setResponseBody("{\"successful\":" + false + ",\"sessionOver\":" + true + "}");
        ctx.set("isSuccess", false);
	}

}
