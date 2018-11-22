package com.wmz.web.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmz.common.entity.Result;
import com.wmz.web.hystrix.AuthFallback;


@FeignClient(name="service-auth",fallback=AuthFallback.class)
public interface AuthClient {
	
	@RequestMapping(value="/auth/getUsers",method=RequestMethod.POST)
	public Object getUsers(@RequestParam("params") Map params);
	
	@RequestMapping(value="/auth/userLogin",method=RequestMethod.POST)
	public Result userLogin(@RequestParam("params") Map params);
	
	@RequestMapping(value="/auth/loginOut",method=RequestMethod.POST)
	public Result loginOut(@RequestBody Map params);

}
