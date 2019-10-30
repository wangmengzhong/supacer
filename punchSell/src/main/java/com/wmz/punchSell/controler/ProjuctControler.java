package com.wmz.punchSell.controler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wmz.punchSell.service.IProductService;

@RestController
@RequestMapping(value = "/sell", produces = "application/json;charset=UTF-8")
public class ProjuctControler {

	@Autowired
	private IProductService  productService;
	
	@RequestMapping("/getProjucts")
	public Object getUsers(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		return productService.listAll();
		//return "123";
	}
}
