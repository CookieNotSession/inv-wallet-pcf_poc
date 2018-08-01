package com.test.inv.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinningList {

	@Id
	private String invTerm;

	private String superPrizeNo; //<千萬特獎號碼>
	private String spcPrizeNo;   //<特獎號碼>
	private String spcPrizeNo2;   //<特獎號碼 2>
	private String spcPrizeNo3;   //<特獎號碼 3>
	private String firstPrizeNo1;   //<頭獎號碼 1>
	private String firstPrizeNo2;   //<頭獎號碼 2>
	private String firstPrizeNo3;   //<頭獎號碼 3>
	private String firstPrizeNo4;   //<頭獎號碼 4>
	private String firstPrizeNo5;   //<頭獎號碼 5>
	private String firstPrizeNo6;   //<頭獎號碼 6>
	private String firstPrizeNo7;   //<頭獎號碼 7>
	private String firstPrizeNo8;   //<頭獎號碼 8>
	private String firstPrizeNo9;   //<頭獎號碼 9>
	private String firstPrizeNo10;   //<頭獎號碼 10>
	private String sixthPrizeNo1;   //<六獎號碼 1>
	private String sixthPrizeNo2;   //<六獎號碼 2>
	private String sixthPrizeNo3;   //<六獎號碼 3>
	private Integer superPrizeAmt;   //<千萬特獎金額>
	private Integer spcPrizeAmt;   //<特獎金額>
	private Integer firstPrizeAmt;   //<頭獎金額>
	private Integer secondPrizeAmt;   //<二獎金額>
	private Integer thirdPrizeAmt;   //<三獎金額>
	private Integer fourthPrizeAmt;   //<四獎金額>
	private Integer fifthPrizeAmt;   //<五獎金額>
	private Integer sixthPrizeAmt;   //<六獎金額>
	private Integer sixthPrizeNo4;   //<六獎號碼 4>
	private Integer sixthPrizeNo5;   //<六獎號碼 5>
	private Integer sixthPrizeNo6;   //<六獎號碼 6>
	
	

}
