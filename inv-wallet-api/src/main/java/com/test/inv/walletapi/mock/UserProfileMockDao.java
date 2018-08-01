package com.test.inv.walletapi.mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.test.inv.walletapi.entity.UserProfile;

@Repository
public class UserProfileMockDao {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${mock.users}")
	private String mockUsers;
	
	private Map<String,UserProfile> users = new HashMap<String,UserProfile>();
	
	@PostConstruct
	public void init() {
		for (String s : Arrays.asList(mockUsers.split("&"))) {
			logger.info("user string : {}",s);
			//mock.users=1|richard|3J0002|/B27.PO2|ieoasl2180&
			List<String> user = Arrays.asList(s.split("\\|"));
			
			UserProfile pro = new UserProfile();
			pro.setUid(Integer.parseInt(user.get(0)));
			pro.setName(user.get(1));
			pro.setCardType(user.get(2));
			pro.setCardNo(user.get(3));
			pro.setCardEncrypt(user.get(4));
			users.put(pro.getName(), pro);
			
		}
		logger.info("users : {}",users);

	}
	
	public UserProfile findByName(String name) {
		return users.get(name);
		
	}

}
