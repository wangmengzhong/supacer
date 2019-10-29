package com.qcl.form;

import lombok.Data;

/**
 * 编程小石头：2501902696（微信）
 */
public class PictureForm {

    private Integer picId;
    private String picUrl;
    private String picMessage;
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getPicMessage() {
		return picMessage;
	}
	public void setPicMessage(String picMessage) {
		this.picMessage = picMessage;
	}
}
