package com.qcl.form;

import lombok.Data;

/**
 * 编程小石头：2501902696（微信）
 */
public class CategoryForm {

    private Integer categoryId;

    public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	/** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
