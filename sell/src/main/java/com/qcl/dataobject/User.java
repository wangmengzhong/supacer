package com.qcl.dataobject;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

/**
 * 编程小石头：2501902696（微信）
 * 用户信息表
 */
@Entity
@DynamicUpdate
@DynamicInsert
public class User {

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Id
    @GeneratedValue
    private int id;
    private String username;
    private String phone;
    private String openid;
    private String zhuohao;//桌号
    private String renshu;//用餐人数

    private Date createTime;
    private Date updateTime;
}
