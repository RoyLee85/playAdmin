package com.admin.VO;

public class OptionsVO {
	private int oNum;
	private String oName1;
	private String oName2;
	private int oQuantity;
	private int oPrice;
	private int gNum;
	
	public OptionsVO(){}

	public OptionsVO(int oNum, String oName1, String oName2, int oQuantity,
			int oPrice, int gNum) {
		super();
		this.oNum = oNum;
		this.oName1 = oName1;
		this.oName2 = oName2;
		this.oQuantity = oQuantity;
		this.oPrice = oPrice;
		this.gNum = gNum;
	}

	public int getoNum() {
		return oNum;
	}

	public void setoNum(int oNum) {
		this.oNum = oNum;
	}

	public String getoName1() {
		return oName1;
	}

	public void setoName1(String oName1) {
		this.oName1 = oName1;
	}

	public String getoName2() {
		return oName2;
	}

	public void setoName2(String oName2) {
		this.oName2 = oName2;
	}

	public int getoQuantity() {
		return oQuantity;
	}

	public void setoQuantity(int oQuantity) {
		this.oQuantity = oQuantity;
	}

	public int getoPrice() {
		return oPrice;
	}

	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
	}

	public int getgNum() {
		return gNum;
	}

	public void setgNum(int gNum) {
		this.gNum = gNum;
	}

	
}


