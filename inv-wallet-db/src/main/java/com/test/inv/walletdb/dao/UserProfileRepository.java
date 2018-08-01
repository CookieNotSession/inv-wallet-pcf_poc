package com.test.inv.walletdb.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.inv.walletdb.entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	
	
	List<UserProfile> findByName(String name);
	
	List<UserProfile> findByNameLike(String name);
	
	@Transactional
	@Modifying
	@Query("update UserProfile u set u.name = ?1 where u.uid = ?2")
	int modifyNameById(String  name, Long id);
}
