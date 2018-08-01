package com.test.inv.repo;

import org.springframework.stereotype.Repository;

import com.test.inv.entity.UserData;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long>{
	
	public UserData findByName(String name);

}
