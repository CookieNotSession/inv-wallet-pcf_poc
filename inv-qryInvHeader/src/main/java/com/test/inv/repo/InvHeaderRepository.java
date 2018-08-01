package com.test.inv.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.inv.entity.InvHeader;

@Repository
public interface InvHeaderRepository 
	extends JpaRepository<InvHeader, String>{

}
