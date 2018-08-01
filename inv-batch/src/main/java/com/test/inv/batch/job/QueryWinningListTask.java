package com.test.inv.batch.job;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.test.inv.entity.WinningList;
import com.test.inv.repo.WinningListRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@SuppressWarnings("rawtypes")
public class QueryWinningListTask {
	
	@Value("${adapter.winningList}")
	private String apiUrl;	
	
	@Autowired
	private RestTemplate restTemplate;

	
	@Autowired
	private WinningListRepository winningListRepository;
	
	@Scheduled(cron="${cron.scheduling.QueryWinningListTask}")
	public void doJob(){
		try {
		log.info("QueryWinningListTask job start ....");
	    String invTerm = "10702";
	    String requestUrl = apiUrl+"/"+invTerm;
		log.info("requestUrl:{}",requestUrl);
		LinkedHashMap result = restTemplate.getForObject(requestUrl, LinkedHashMap.class);
		log.info("Http result => {}",result);
		persist(invTerm,result);
		}catch(Exception e) {
			log.error("error :",e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void persist(String invTerm, LinkedHashMap result) throws IllegalAccessException, InvocationTargetException {
		if (winningListRepository.findById(invTerm) != null) {
			log.info("{} is exist",invTerm);
			
		}
		
		WinningList entity = new WinningList();
		BeanUtils.populate(entity, result);
		entity.setInvTerm(invTerm);
		log.info("entity : {}",entity);
		winningListRepository.save(entity);
		
	}

}
