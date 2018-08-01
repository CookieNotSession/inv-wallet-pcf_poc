package com.test.inv.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.test.inv.entity.UserData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDataCache {
	
	@Autowired
	private UserDataRepository userDataRepository;
	
    @Cacheable(value = "userdata", key = "'userdata'.concat(#id.toString())")
	public UserData findById(Long id) {
		log.info("get data from db......");
		return userDataRepository.findById(id).get();
	}
    
    @Cacheable(value = "userdata", key = "'userdata'.concat(#name.toString())")
    public UserData findByName(String name) {
        log.info("findByName query from db, name: {}", name);
        return userDataRepository.findByName(name);
    }

    
    @CachePut(value = "userdata", key = "'userdata'.concat(#customer.id.toString())")
    public void customer(UserData user) {
        log.info("update db, user: {}", user.toString());
        userDataRepository.save(user);
    }

    
    @CacheEvict(value = "userdata", key = "'userdata'.concat(#uid.toString())")
    public void remove(Long id) {
        log.info("remove from db, id: {}", id);
        userDataRepository.deleteById(id);
    }
    
    public List<UserData> findAll() {
    		return userDataRepository.findAll();
    }
    
}
