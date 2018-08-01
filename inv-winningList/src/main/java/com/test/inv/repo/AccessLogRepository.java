package com.test.inv.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.inv.entity.AccessLog;

@Repository
public interface AccessLogRepository 
	extends JpaRepository<AccessLog, Long>{
	
}
