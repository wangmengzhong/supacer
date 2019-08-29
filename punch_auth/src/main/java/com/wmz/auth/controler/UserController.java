package com.wmz.auth.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wmz.auth.dao.UserMapper;
import com.wmz.common.service.BaseService;
import com.wmz.auth.service.UserService;
import com.wmz.common.controler.DefaultController;
import com.wmz.common.entity.Result;
import com.wmz.common.entity.User;
import com.wmz.common.enums.ErrorEnum;
import com.wmz.common.exception.SpaException;
import com.wmz.common.util.ResultUtil;

@RestController
@RequestMapping("/auth")
public class UserController extends DefaultController<User> {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Override
	public BaseService getBaseService() {
		return userService;
	}

	private static Logger log = Logger.getLogger(UserController.class);

	// 用户登录
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping("/userLogin")
	public Result login(HttpServletRequest request,@RequestParam String name, @RequestParam String password) {
		List list = userService.userLogin(name, password);
		Map<String, Object> map = new HashMap<String, Object>();
		if (list.size() > 0) {
			
			return ResultUtil.success(list.get(0));
		} else {
			return ResultUtil.failed("登录失败");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping("/loginOut")
	public Result loginOut(HttpServletRequest request) {
	
		return ResultUtil.success(true);
	}

	@RequestMapping("/getUser")
	public User getUser(Long id) {
		return (User) super.findById(id + "");
	}

	@RequestMapping("/getUsers")
	public Object getUsers(HttpServletRequest request, @RequestParam Map<String, Object> parameters) {

		/*
		 * try { System.out.println("延时2秒");
		 * Thread.currentThread().sleep(2000);//毫秒 } catch(Exception e){}
		 */

		return ResultUtil.success(super.findByPage(parameters));
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@RequestMapping("/add")
	public Result<User> save(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
		}
		if (true) {
			throw new SpaException(ErrorEnum.UNKNOW_ERROR);
		} else {
			userMapper.insert(user);
			return ResultUtil.success(true);
		}
	}

	@RequestMapping(value = "update")
	public void update(User user) {
		userMapper.update(user);
	}

	@RequestMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		userMapper.delete(id);
	}
	
	@Value("${word}")
	private String word;
	
	@RequestMapping("/test")
	public Object test(HttpServletRequest request) {
		return word;
	}

}