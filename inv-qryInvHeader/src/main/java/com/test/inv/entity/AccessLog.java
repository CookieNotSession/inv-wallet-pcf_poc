package com.test.inv.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
