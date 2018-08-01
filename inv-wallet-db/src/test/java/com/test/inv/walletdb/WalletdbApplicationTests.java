package com.test.inv.walletdb;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import com.test.inv.walletdb.dao.UserProfileRepository;
import com.test.inv.walletdb.entity.UserProfile;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WalletdbApplicationTests {

	@Autowired
	private UserProfileRepository userRepository;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testUserProfiles() throws Exception {
		String s ="4|richard|3J0002|/B27.PO2|ieoasl2180";
		List<String> user = Arrays.asList(s.split("\\|"));	
		UserProfile pro = new UserProfile();
		//pro.setUid(Long.parseLong(user.get(0)));
		pro.setName(user.get(1));
		pro.setCardType(user.get(2));
		pro.setCardNo(user.get(3));
		pro.setCardEncrypt(user.get(4));
		//userRepository.save(pro);

		UserProfile profile = new UserProfile();
		profile.setUid(3L);
		Example<UserProfile> example = Example.of(profile);
		log.info("findOne : {}",userRepository.findOne(example));
		log.info("findAll : {}",userRepository.findAll());
		log.info("exists : {}",userRepository.exists(example));
		log.info("count : {}",userRepository.count());
		//userRepository.delete(pro);
		log.info("modifyNameById : {}",userRepository.modifyNameById("richardkang", 3L));

		log.info("findByNameLike : {}",userRepository.findByNameLike("rich%"));
		

	}
}
