package com.test.inv.walletdb.ctl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.inv.walletdb.dao.AccessLogRepository;
import com.test.inv.walletdb.dao.UserProfileRepository;
import com.test.inv.walletdb.entity.AccessLog;
import com.test.inv.walletdb.entity.UserProfile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class RestApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private AccessLogRepository accessLogRepository;
	
	@GetMapping(value = "/userProfile/{name}")
	public ResponseEntity<UserProfile> userProfileData(@PathVariable String name) {

		List<UserProfile> userProfile = userProfileRepository.findByName(name);
		if (userProfile != null && userProfile.size() > 0) {
			return ResponseEntity.ok().body(userProfile.get(0));
		}else {
			return ResponseEntity.ok().body(null);
		}
	}
	
	@GetMapping(value = "/userProfiles")
	public ResponseEntity<List<UserProfile>> userProfiles() {

		List<UserProfile> userProfileList = userProfileRepository.findAll();
		return ResponseEntity.ok(userProfileList);
	}
	
	@PostMapping(value = "/userProfile")
	@ResponseStatus(HttpStatus.OK)
	public void createUserProfile(@RequestBody UserProfile userProfile) {
		logger.info("userProfile => {}",userProfile);
		userProfileRepository.saveAndFlush(userProfile);
	}
	
	
	@GetMapping(value = "/logs")
	public ResponseEntity<List<AccessLog>> logs() {

		List<AccessLog> accessLogList = accessLogRepository.findAll();
		return ResponseEntity.ok(accessLogList);
	}
	
	@PostMapping(value = "/log")
	@ResponseStatus(HttpStatus.OK)
	public void createLog(@RequestBody AccessLog accessLog) {
		logger.info("accessLog => {}",accessLog);
		accessLogRepository.saveAndFlush(accessLog);
	}
	
}
