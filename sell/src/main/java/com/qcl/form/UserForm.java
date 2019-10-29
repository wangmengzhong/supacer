package com.qcl.form;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 编程小石头：2501902696（微信）
 */
public class UserForm {


    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getZhuohao() {
		return zhuohao;
	}
	public void setZhuohao(String zhuohao) {
		this.zhuohao = zhuohao;
	}
	public String getRenshu() {
		return renshu;
	}
	public void setRenshu(String renshu) {
		this.renshu = renshu;
	}
	/**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String username;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;


    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    private String zhuohao;
    private String renshu;
}
