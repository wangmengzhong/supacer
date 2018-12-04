package com.wmz.punch_zuul.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wmz.punch_zuul.filter.UrlFilter;


@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setName("zullurlFilter");
		registrationBean.setFilter(myFilter());
		registrationBean.setOrder(1);
		List<String> urlList = new ArrayList<String>();
		urlList.add("/rest/*");
		registrationBean.setUrlPatterns(urlList);
		return registrationBean;
	}

	@Bean
	public Filter myFilter() {
		return new UrlFilter();
	}

}
