package com.test.inv.walletdb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.inv.walletdb.entity.UserProfile;

@Repository("userProfileRepository")
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	
	UserProfile findByName(String name);
}
