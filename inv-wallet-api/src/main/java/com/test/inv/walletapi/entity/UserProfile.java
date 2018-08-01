package com.test.inv.walletapi.entity;



public class UserProfile {
	
	private Integer uid;
	private String name;
	private String cardType;
	private String cardNo;
	private String cardEncrypt;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardEncrypt() {
		return cardEncrypt;
	}
	public void setCardEncrypt(String cardEncrypt) {
		this.cardEncrypt = cardEncrypt;
	}
	@Override
	public String toString() {
		return "UserProfile [uid=" + uid + ", name=" + name + ", cardType=" + cardType + ", cardNo=" + cardNo
				+ ", cardEncrypt=" + cardEncrypt + "]";
	}
	
	

}
