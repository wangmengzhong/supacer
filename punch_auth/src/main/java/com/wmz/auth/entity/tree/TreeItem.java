package com.wmz.auth.entity.tree;

public class TreeItem {

	private String name;
	
	private String title;

	private String id;

	private String full_name;

	private String parent_id;
	
	private Boolean is_leaf;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIs_leaf() {
		return is_leaf;
	}

	public void setIs_leaf(Boolean is_leaf) {
		this.is_leaf = is_leaf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
