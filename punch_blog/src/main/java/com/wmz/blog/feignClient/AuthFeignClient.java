package com.wmz.blog.feignClient;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmz.blog.hystrix.AuthFeignFallback;

@FeignClient(name="service-auth",fallback=AuthFeignFallback.class)
public interface AuthFeignClient {
	
	@RequestMapping(value="/auth/getUsers",method=RequestMethod.POST)
	public Object getUsers(@RequestParam("params") Map params);

}
