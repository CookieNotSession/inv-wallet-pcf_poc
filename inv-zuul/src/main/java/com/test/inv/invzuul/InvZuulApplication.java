package com.test.inv.invzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.test.inv.invzuul.filter.TokenFilter;

@SpringBootApplication
@EnableZuulProxy
public class InvZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvZuulApplication.class, args);
	}
	
	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}
}
