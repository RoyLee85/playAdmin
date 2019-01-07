package com.admin.VO;

import java.sql.Date;


public class GoodsVO {
	private int gNum;
	private String title;
	private String content;
	private int price;
	private String category;
	private String mCompany;
	private String origin;
	private int dBasePrice;
	private int dPrice;
	private Date gRegDate;
	private int hit;

	public GoodsVO(){}

	public GoodsVO(int gNum, String title, String content, int price,
			String category, String mCompany, String origin, int dBasePrice,
			int dPrice, Date gRegDate, int hit) {
		super();
		this.gNum = gNum;
		this.title = title;
		this.content = content;
		this.price = price;
		this.category = category;
		this.mCompany = mCompany;
		this.origin = origin;
		this.dBasePrice = dBasePrice;
		this.dPrice = dPrice;
		this.gRegDate = gRegDate;
		this.hit = hit;
	}

	public int getgNum() {
		return gNum;
	}

	public void setgNum(int gNum) {
		this.gNum = gNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getmCompany() {
		return mCompany;
	}

	public void setmCompany(String mCompany) {
		this.mCompany = mCompany;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getdBasePrice() {
		return dBasePrice;
	}

	public void setdBasePrice(int dBasePrice) {
		this.dBasePrice = dBasePrice;
	}

	public int getdPrice() {
		return dPrice;
	}

	public void setdPrice(int dPrice) {
		this.dPrice = dPrice;
	}

	public Date getgRegDate() {
		return gRegDate;
	}

	public void setgRegDate(Date gRegDate) {
		this.gRegDate = gRegDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	};
	
	
}


