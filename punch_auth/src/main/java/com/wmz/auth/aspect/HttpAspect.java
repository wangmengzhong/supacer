package com.wmz.auth.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
/**
 * 日志切面
 * @author Administrator
 */
@Aspect
@Component
public class HttpAspect {

	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

	Gson gson = new Gson();

	@Pointcut("execution(public * com.wmz.auth.controller.*.*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		logger.info("---log--before---");
		System.out.println("---log--before---");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		logger.info("url={}", request.getRequestURL());

		logger.info("methed={}", request.getMethod());

		logger.info("ip={}", request.getRemoteAddr());

		// 类方法
		logger.info("class_method={}",
				joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

		// 类参数
		logger.info("args={}", joinPoint.getArgs());
	}

	@After("log()")
	public void doAfter() {
		logger.info("---log--after---");
	}

	@AfterReturning(returning = "object", pointcut = "log()")
	public void doAfterReturning(Object object) {
		logger.info("response={}", gson.toJson(object));
	}

}
