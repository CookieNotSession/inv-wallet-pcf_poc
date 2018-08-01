package com.test.inv.walletdb;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.test.inv.walletdb.dao.UserProfileRepository;
import com.test.inv.walletdb.entity.UserProfile;

@SpringBootApplication
@EnableDiscoveryClient
public class WalletdbApplication  implements CommandLineRunner {
	protected final Logger logger = LoggerFactory.getLogger(getClass());


	@Value("${mock.users}")
	private String mockUsers;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WalletdbApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		for (String s : Arrays.asList(mockUsers.split("&"))) {
			logger.info("user string : {}",s);
			//mock.users=1|richard|3J0002|/B27.PO2|ieoasl2180&
			List<String> user = Arrays.asList(s.split("\\|"));
			if (userProfileRepository.findByName(user.get(1)).size() > 0) continue;
			
			UserProfile pro = new UserProfile();
			pro.setUid(Long.parseLong(user.get(0)));
			pro.setName(user.get(1));
			pro.setCardType(user.get(2));
			pro.setCardNo(user.get(3));
			pro.setCardEncrypt(user.get(4));
			userProfileRepository.save(pro);
		}
	}
}
