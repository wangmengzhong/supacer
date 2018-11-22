package com.wmz.auth.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wmz.common.entity.Result;
import com.wmz.common.exception.SpaException;
import com.wmz.common.util.ResultUtil;


/**
 * 统一处理异常
 * @author Administrator
 */
@ControllerAdvice
public class ExceptionHandle {
	
	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result handle(Exception e) {
		logger.error("【系统异常】{}",e);
		if (e instanceof SpaException) {
			SpaException spaException = (SpaException) e;
			return ResultUtil.error(spaException.getCode(), spaException.getMessage());
		} else {
			return ResultUtil.error(100, e.getMessage());
		}
	}

}
