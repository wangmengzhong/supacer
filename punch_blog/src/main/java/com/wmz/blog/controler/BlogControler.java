package com.wmz.blog.controler;

import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.wmz.blog.entity.Blog;
import com.wmz.blog.feignClient.AuthFeignClient;
import com.wmz.blog.service.BlogService;
import com.wmz.blog.util.TokenUtil;
import com.wmz.common.controler.DefaultController;
import com.wmz.common.entity.ImgFileInfo;
import com.wmz.common.entity.Result;
import com.wmz.common.entity.User;
import com.wmz.common.service.BaseService;
import com.wmz.common.util.FileUploadUtil;
import com.wmz.common.util.ResultUtil;
import com.wmz.common.util.SessionUtil;

@RestController
@RequestMapping(value = "/blog", produces = "application/json;charset=UTF-8")
public class BlogControler extends DefaultController<Blog> {

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	private BlogService blogService;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	AuthFeignClient authFeignClient;

	@Override
	public BaseService getBaseService() {
		return blogService;
	}

	@RequestMapping("/getUsers")
	public Object getUsers(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		return authFeignClient.getUsers(params);
	}

	@RequestMapping(value = ("/getBlogList"))
	public Object getBlogList(HttpServletRequest request, @RequestBody Map<String, Object> params) {
		params.put("createdName", "wmz");
		return ResultUtil.success(super.findByPage(params));
	}

	@RequestMapping(value = ("/addBlog"))
	public Result addBlog(HttpServletRequest request, @RequestBody Blog blog, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
		}
		User nowUser = tokenUtil.getUserInfo(request.getParameter("sessionId").toString());
		blog.setCreatedName(nowUser.getUserName());
		blog.setCreatedBy(nowUser.getId());
		blog.setCreatedDt(new Date());
		blog = (Blog) super.add(blog);
		return ResultUtil.success(blog);
	}

	@RequestMapping("/delBlog")
	public Result delBlog(HttpServletRequest request, @RequestBody String[] ids) {
		for (String id : ids) {
			super.delete(id);
		}
		return ResultUtil.success(true);
	}

	@RequestMapping("/updateBlog")
	public Result updateBlog(HttpServletRequest request, @RequestBody Blog blog) {
		super.update(blog);
		return ResultUtil.success(true);
	}

	@RequestMapping("/getBlogInf")
	public Result getBlogInf(HttpServletRequest request, @RequestBody Blog blog) {
		blog = (Blog) findById(blog.getDiaryId());
		return ResultUtil.success(blog);
	}

	@RequestMapping("/uploadImages")
	public Object uploadImages(HttpServletRequest request) {
		ImgFileInfo imgFileInfo = FileUploadUtil.fileUpload(request, "");

		return imgFileInfo.getStoreImgAddr();
	}

}
