package com.test.inv.walletdb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.inv.walletdb.entity.AccessLog;

@Repository("accessLogRepository")
public interface AccessLogRepository 
	extends JpaRepository<AccessLog, Long>{
	
}
