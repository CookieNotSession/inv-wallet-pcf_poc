package com.test.inv.winning.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.test.inv.entity.AccessLog;
import com.test.inv.entity.WinningList;
import com.test.inv.repo.AccessLogRepository;
import com.test.inv.repo.WinningListRepository;

import com.test.inv.winning.ctl.AdapterRemote;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WinningListService {
	@Autowired
	private AdapterRemote adapterRemote;
	
	@Autowired
	private WinningListRepository winningListRepository;

	@Autowired
    private AccessLogRepository accessLogRepository;
	
	@Transactional
    @Cacheable(value = "winning", key = "'winning'.concat(#invTerm.toString())")
	public String query(String invTerm) throws Exception{
		log.info(">>>>>>>> execute query. not cache");
		Optional<WinningList> o = winningListRepository.findById(invTerm);
		if (o.isPresent()) {
			WinningList entity 
			= winningListRepository.findById(invTerm).get();
			log.info(">>>>>>>>> {} is exist in db",invTerm);
			log(invTerm, entity.getJsonStr());
			return entity.getJsonStr();

		}
		
		String apiResult = adapterRemote.winningList(invTerm);
		log.info(">>>>>>>>> {} call adapter service",invTerm);
		persist(invTerm, apiResult);
		log(invTerm, apiResult);
		return apiResult;
	}
	
	public void persist(String invTerm, String apiResult) throws IllegalAccessException, InvocationTargetException {

		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> result = springParser.parseMap(apiResult);
		
		WinningList entity = new WinningList();
		BeanUtils.populate(entity, result);
		entity.setInvTerm(invTerm);
		entity.setJsonStr(apiResult);
		log.info("entity : {}",entity);
		winningListRepository.save(entity);
	}
	
	public void log(String invTerm, String apiResult) throws Exception {
        AccessLog accessLog = new AccessLog();
        accessLog.setApiName("winningList");
        accessLog.setInvokeTime( new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        accessLog.setInput(invTerm);
        accessLog.setOutput(apiResult);
        accessLogRepository.save(accessLog);
		
	}
}
