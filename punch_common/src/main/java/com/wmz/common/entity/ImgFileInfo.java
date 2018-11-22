package com.wmz.common.entity;

public class ImgFileInfo {
	
	private String imgUuid;//数据库内图片id
	
	private String imgName;//图片名称 
	
	private String imgType;//图片类型 (扩展名)
	
	private String storeImgName;//服务器图片名称
	
	private String storeImgAddr;//服务器图片地址
	
	private String type;//用途类型 
	
	private boolean successful;//上传是否成功
	
	private String errInfo;//错误信息
	
	private boolean connected=true;//是否已经跟其他表关联， false：上传成功后还需要保存其他表的信息 来关联此图片
	
	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String getStoreImgName() {
		return storeImgName;
	}

	public void setStoreImgName(String storeImgName) {
		this.storeImgName = storeImgName;
	}

	public String getStoreImgAddr() {
		return storeImgAddr;
	}

	public void setStoreImgAddr(String storeImgAddr) {
		this.storeImgAddr = storeImgAddr;
	}

	public String getImgUuid() {
		return imgUuid;
	}

	public void setImgUuid(String imgUuid) {
		this.imgUuid = imgUuid;
	}

	
	
	
	

}
