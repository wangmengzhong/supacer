package com.wmz.common.enums;

public enum ErrorEnum {
	
	UNKNOW_ERROR(-1,"未知错误"),
	SUCCESS(0,"成功"),
	;
	
	ErrorEnum(Integer code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	private Integer code;
	
	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
