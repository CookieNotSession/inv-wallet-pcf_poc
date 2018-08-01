package com.test.inv.util.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.inv.walletapi.service.ServiceObject;


@Service
public class HttpApi extends ServiceObject{
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public Object doGet(String url) {
		Object result = restTemplate.getForObject(url, Object.class);
		logger.info("result : {}",result);
		return result;
	}
	
	
	public Object doPost(String url) {
		Object result = restTemplate.getForObject(url, Object.class);
		logger.info("result : {}",result);
		return result;
	}

}
