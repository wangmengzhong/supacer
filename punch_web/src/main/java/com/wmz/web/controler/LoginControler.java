package com.wmz.web.controler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wmz.common.entity.Result;
import com.wmz.common.entity.User;
import com.wmz.common.safe.rsa.SecurityUtils;
import com.wmz.common.util.ResultUtil;
import com.wmz.common.util.SessionUtil;
import com.wmz.web.client.AuthClient;
import com.wmz.web.redis.CacheService;
import com.wmz.web.util.TokenUtil;

@RestController
@RequestMapping(value = "/rest/system", produces = "application/json;charset=UTF-8")
public class LoginControler {
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	CacheService cacheService;

	@Autowired
	AuthClient authClient;

	/**
	 * 判断用户是否登录并反回登录信息
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/isLogined")
	public Object isLogin(HttpServletRequest request, @RequestBody Map<String, Object> params) {
		if(params.get("sessionId")==null){
			return ResultUtil.failed("未登录");
		}
		User user=tokenUtil.getUserInfo(params.get("sessionId").toString());
		if (user == null) {
			System.out.println("---用户未登录---");
			return ResultUtil.failed("未登录");
		} else {
			return ResultUtil.success(user);
		}
	}
	
	@RequestMapping("/loginOut")
	public Object loginOut(HttpServletRequest request,@RequestBody Map params) {
		 Result result =authClient.loginOut(params);
		 if((Boolean)(result.getData())==true){
			 cacheService.remove(params.get("sessionId").toString());
			 System.out.println("退出成功！");
		 }
		 return result;
	}

	@RequestMapping("/getUsers")
	public Object getUsers(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		return authClient.getUsers(params);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/userLogin")
	public Object userLogin(HttpSession session,HttpServletRequest request, @RequestParam Map<String, Object> params) {
		System.out.println("web:"+session.getId());
		Map map=new HashMap();
		Result<User> result=(Result) authClient.userLogin(params);
		if(result.getCode()==0){
			String sessionId=tokenUtil.setUserInfo(result.getData(),(long)36000);
			System.out.println("-----------登录成功！------------");
			map.put("sessionId", sessionId);
		}
		map.put("userInfo", result.getData());
		return ResultUtil.success(map);
	}
	
	
}
