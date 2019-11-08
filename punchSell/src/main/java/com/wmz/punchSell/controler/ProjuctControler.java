package com.wmz.punchSell.controler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wmz.punchSell.domain.ProductCategory;
import com.wmz.punchSell.domain.ProductInfo;
import com.wmz.punchSell.service.IProductCategoryService;
import com.wmz.punchSell.service.IProductService;

@RestController
@RequestMapping(value = "/sell", produces = "application/json;charset=UTF-8")
public class ProjuctControler {

	@Autowired
	private IProductService  productService;
	
	@Autowired
    private IProductCategoryService productCategoryService;
	
	@RequestMapping("/getProjucts")
	public Object getUsers(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		
		List<ProductInfo> list=productService.listAll();
		
		List<ProductCategory> clist=productCategoryService.listAll();
		
		
		return productService.listAll();
		//return "123";
	}
}
