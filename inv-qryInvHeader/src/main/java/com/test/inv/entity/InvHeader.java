package com.test.inv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvHeader {

	@Id
	private String invNum; // 發票號碼
	private String invDate; // 發票開立日期
	private String sellerName; // 賣方名稱
	private String invStatus; // 發票狀態
	private String invPeriod; // 對獎發票期別
	private String sellerBan; // 賣方營業人統編
	private String sellerAddress; // 賣方營業人地址
	private String invoiceTime; // 發票開立時間
	private String buyerBan; // 買方營業人統編

	@Column(length = 20000)
	private String jsonStr;

}
