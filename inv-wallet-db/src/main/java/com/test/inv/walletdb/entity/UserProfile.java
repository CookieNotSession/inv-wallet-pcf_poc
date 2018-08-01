package com.test.inv.walletdb.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserProfile {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
    
	private String name;
	private String cardType;
	private String cardNo;
	private String cardEncrypt;

	@Override
	public String toString() {
		return "UserProfile [uid=" + uid + ", name=" + name + ", cardType=" + cardType + ", cardNo=" + cardNo
				+ ", cardEncrypt=" + cardEncrypt + "]";
	}
	
}	
