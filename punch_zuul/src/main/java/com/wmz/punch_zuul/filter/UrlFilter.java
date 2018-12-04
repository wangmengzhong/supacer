package com.wmz.punch_zuul.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.wmz.common.entity.User;
import com.wmz.punch_zuul.util.TokenUtil;

public class UrlFilter implements Filter {

	

	@Autowired
	TokenUtil tokenUtil;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings({ "unused", "static-access" })
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		StringBuffer url = req.getRequestURL();
		url.append("#");
		System.out.println("punch_zull--- url:" + url + "---ip: " + getIp(req) + "---mac: " );

		if (isWhitURL(url.toString())) {
			chain.doFilter(request, response);
		} else {

			ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(req);

			Map map = this.getBodyString(requestWrapper.getReader());
			if (map == null) {
				returnError(resp);
			} else {
				String sessionId = map.get("sessionId") != null ? map.get("sessionId").toString() : null;
				if (sessionId == null) {
					returnError(resp);
				} else {
					User user = tokenUtil.getUserInfo(sessionId);
					if (user != null) {
						chain.doFilter(requestWrapper, response);
					} else {
						returnError(resp);
					}
				}
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void returnError(HttpServletResponse resp) {
		String error = "{\"successful\":" + false + ",\"sessionOver\":" + true + "}";
		Writer writer = null;
		resp.setContentType("text/json;charset=UTF-8");
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		try {
			writer = resp.getWriter();
			writer.write(error);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public String getIp(HttpServletRequest req) {
		
		return req.getRemoteAddr();
	}

}
