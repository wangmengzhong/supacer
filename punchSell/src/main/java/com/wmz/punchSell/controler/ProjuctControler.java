package com.wmz.punchSell.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/vxgetProjucts")
	public Object vxgetProjucts(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		
		List<ProductInfo> productList=productService.listAll();
		List<ProductCategory> typelist=productCategoryService.listAll();
		List groups= new ArrayList();
		
		for(int i=0;i<typelist.size();i++){
			Map category=new HashMap();
			List group = new ArrayList();
			ProductCategory item= typelist.get(i);
			String categoryType=(String) item.getCategoryType();
			category.put("id", item.getCategoryId());
			category.put("name", item.getCategoryName());
			for(int j=0;j<productList.size();j++){
				ProductInfo product=productList.get(j);
				Map map=JSON.parseObject(JSON.toJSONString(product));
				map.put("num", 0);
				if(product.getCategoryType().equals(categoryType)){
					group.add(map);
				}
			}
			category.put("group", group);
			groups.add(category);
		}
		return groups;
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/getProjucts")
	public Object getProjucts(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		
		List<ProductInfo> productList=productService.listAll();
		List<ProductCategory> typelist=productCategoryService.listAll();
		List groups= new ArrayList();
		
		return productList;
	}
	
	@RequestMapping("/getOne")
	public Object getOne(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		
		ProductInfo p=productService.findOne(params.get("id").toString());
		return p;
	}
}
