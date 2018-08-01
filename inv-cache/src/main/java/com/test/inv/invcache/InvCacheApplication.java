package com.test.inv.invcache;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.test.inv.entity.UserData;
import com.test.inv.repo.UserDataRepository;


@SpringBootApplication
@EnableJpaRepositories("com.test.inv.repo")
@EntityScan("com.test.inv.entity")
@ComponentScan("com.test.inv")
@EnableDiscoveryClient
@EnableCaching
public class InvCacheApplication  implements CommandLineRunner {
	@Autowired
	private UserDataRepository userDataRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(InvCacheApplication.class, args);
	}
	
	@Override
	@Transactional
	public void run(String... arg0) throws Exception {
		if (userDataRepository.findByName("richard") != null) return;
		UserData u = new UserData();
		u.setName("richard");
		u.setAge("40");
		
		userDataRepository.save(u);
	}
}
