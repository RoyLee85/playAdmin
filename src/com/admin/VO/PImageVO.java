package com.admin.VO;

public class PImageVO {
	private int iNum;
	private int gNum;
	private String orgName;
	private String saveName;
	private long fileSize;
	private String path;
	
	public PImageVO(){}

	public PImageVO(int iNum, int gNum, String orgName, String saveName,
			long fileSize, String path) {
		super();
		this.iNum = iNum;
		this.gNum = gNum;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.path = path;
	}

	public int getINum() {
		return iNum;
	}

	public void setINum(int iNum) {
		this.iNum = iNum;
	}

	public int getgNum() {
		return gNum;
	}

	public void setgNum(int gNum) {
		this.gNum = gNum;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}


