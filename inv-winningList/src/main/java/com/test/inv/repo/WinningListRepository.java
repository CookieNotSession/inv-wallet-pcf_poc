package com.test.inv.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.inv.entity.WinningList;

@Repository
public interface WinningListRepository 
	extends JpaRepository<WinningList, String>{

}
