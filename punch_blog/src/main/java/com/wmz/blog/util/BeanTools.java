package com.wmz.blog.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanTools implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	@SuppressWarnings("unchecked")
	public static Object getBean(Class className) {
		try {
			Object bean = applicationContext.getBean(className);
			return bean;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Object getBean(String beanId) {
		try {
			Object bean = applicationContext.getBean(beanId);
			return bean;
		} catch (Exception e) {
			return null;
		}
	}

	/*public static void setApplicationContext1(ApplicationContext context) {
		applicationContext = context;
	}*/
}
