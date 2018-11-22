package com.wmz.common.exception;

import com.wmz.common.enums.ErrorEnum;

/**
 * 自定义异常
 * @author Administrator
 */
public class SpaException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	
	public SpaException(ErrorEnum resultEnum){
		super(resultEnum.getMsg());
		this.code=resultEnum.getCode();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	

}
