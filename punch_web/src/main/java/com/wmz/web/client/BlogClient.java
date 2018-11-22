package com.wmz.web.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmz.web.hystrix.BlogFallback;

@FeignClient(name = "service-blog", fallback = BlogFallback.class)
public interface BlogClient {
	
	@RequestMapping(value = "/blog/getBlogList", method = RequestMethod.POST)
	public Object getBlogList(@RequestBody Map params);
	
	@RequestMapping(value = "/blog/getBlogInf", method = RequestMethod.POST)
	public Object getBlogInf(@RequestBody Map params);
	
	@RequestMapping(value = "/blog/addBlog", method = RequestMethod.POST)
	public Object addBlog(@RequestParam Map<String, Object> map,@RequestBody Map params);
	
	@RequestMapping(value = "/blog/updateBlog", method = RequestMethod.POST)
	public Object updateBlog(@RequestBody Map params);
	
}
