package com.test.inv.walletapi.service;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.inv.util.HttpAPIService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QryWinningListService{

	@Value("${adapter.serverUrl}")
	private String serverUrl;	

	@Value("${adapter.winningList}")
	private String apiUrl;	

	@Resource
	private HttpAPIService httpAPIService;

	@Autowired
	private RestTemplate rest;

	@Autowired 
	private DiscoveryClient client;

	public String doAction(String invTerm) throws Exception {
		String url = serverUrl + apiUrl + "/"+invTerm;
		String result = httpAPIService.doGet(url);
		log.info("Http result => {}",result);
		return result;
	}

	public Object invokeService(String invTerm) throws Exception {
		String url = serverUrl + apiUrl + "/"+invTerm;
		log.info("Http url : {}",url);
		Object result 
		= rest.getForObject(url, Object.class);
		log.info("Http result => {}",result.getClass().getName());
		log.info("Http result => {}",result);
		return result;
	}
	
	public Object invokeLocalService(String invTerm) throws Exception {
		List<ServiceInstance> list = client.getInstances("INV-ADAPTER");
		if (list != null && list.size() > 0 ) {
			URI uri = list.get(0).getUri();
			if (uri !=null ) {
				log.info("Http uri : {}",uri);
				String url = serverUrl + apiUrl + "/"+invTerm;
				Object result = rest.getForObject(url,Object.class);
				log.info("Http result => {}",result.getClass().getName());
				log.info("Http result => {}",result);
				return result;
			}
		}
		return null;
	}

}
