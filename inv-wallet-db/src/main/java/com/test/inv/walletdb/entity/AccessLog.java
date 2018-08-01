package com.test.inv.walletdb.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AccessLog {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    
    private String apiName;
    
    private String invokeTime;
    
    private String input;
    
    @Column(length=20000)
    private String output;

    
}
