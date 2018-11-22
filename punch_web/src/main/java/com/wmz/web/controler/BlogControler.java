package com.wmz.web.controler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.wmz.common.entity.ImgFileInfo;
import com.wmz.common.entity.User;
import com.wmz.common.util.FileUploadUtil;
import com.wmz.common.util.SessionUtil;
import com.wmz.web.client.BlogClient;
import com.wmz.web.redis.CacheService;


@RestController
@RequestMapping(value = "/rest/blog", produces = "application/json;charset=UTF-8")
public class BlogControler {
	
	@Value("${upLoadPath}")
	private String upLoadPath;
	
	@Autowired
	CacheService cacheService;

	@Autowired
	BlogClient blogClient;
	
	Gson gson=new Gson();
	
	@RequestMapping("/getBlogList")
	public Object getBlogList(HttpServletRequest request,@RequestBody Map params) {
		return blogClient.getBlogList(params);
	}

	@RequestMapping("/getBlogInf")
	public Object getBlogInf(HttpServletRequest request,@RequestBody Map params) {
		return blogClient.getBlogInf(params);
	}
	
	@RequestMapping("/addBlog")
	public Object addBlog(HttpServletRequest request,@RequestParam Map<String, Object> params,@RequestBody Map body) {
		return blogClient.addBlog(params,body);
	}
	
	@RequestMapping("/updateBlog")
	public Object updateBlog( HttpServletRequest request,@RequestBody Map params) {
		return blogClient.updateBlog(params);
	}
	
	@RequestMapping("/uploadImages")
	public Object uploadImages(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
		//var file = request.files["myFileName"];
		String filePath=upLoadPath + file.getOriginalFilename();
		File dest = new File(filePath);
		try {
			file.transferTo(dest);
			return "/web/rest/blog/getImage?imgPath="+file.getOriginalFilename();
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping("/getImage")
	public void getImage(HttpServletRequest request,HttpServletResponse response) {
		String imgPath = request.getParameter("imgPath");
		
		byte[] content =FileUploadUtil.getImgByPath(upLoadPath+imgPath);
		response.setContentType("image/JPEG");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			OutputStream os = response.getOutputStream();
			try {
				os.write(content);
			}catch (Exception e){
				System.err.println("图片路径未找到！"+imgPath);
			}
			finally {
				os.flush();
				os.close();
			}
		} catch (IOException e) {
			System.err.println("图片路径未找到！"+imgPath);
		}
	}
	
	@RequestMapping("/cleanImages")
	public void cleanImages(HttpServletRequest request,HttpServletResponse response) {
		
	}
	
}
