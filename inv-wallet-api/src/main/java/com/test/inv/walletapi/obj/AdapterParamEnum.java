package com.test.inv.walletapi.obj;

public enum AdapterParamEnum {
	
	invTerm("invTerm",null),
	UUID("UUID",null),
	appID("appID",null),
	invNum("invNum",null),
	invDate("invDate",null),
	generation("generation","V2"),
	invType("type",null),

	timeStamp("timeStamp",null),
	expTimeStamp("expTimeStamp",null),
	startDate("startDate",null),
	endDate("endDate",null),
	onlyWinningInv("onlyWinningInv",null),
	uuid("uuid",null),
	serial("serial",null),
	signature("signature",null),

	cardType("cardType",null),
	cardNo("cardNo",null),
	cardEncrypt("cardEncrypt",null);
	
	
	
	private String name;

	private String value;
	
	private AdapterParamEnum(String name,String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}	
}
