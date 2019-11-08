package com.wmz.punchSell.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@DynamicInsert
public class ProductCategory {
	
		@Id
	    private Integer categoryId;

		/** 类目名字. */
	    private String categoryName;

	    /** 类目编号. */
	    private Integer categoryType;
	    
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

}
