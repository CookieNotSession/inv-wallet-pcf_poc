package com.test.inv.invheader.service;

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
import com.test.inv.entity.InvHeader;
import com.test.inv.invheader.ctl.AdapterRemote;
import com.test.inv.repo.AccessLogRepository;
import com.test.inv.repo.InvHeaderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QryInvHeaderService {
	@Autowired
	private AdapterRemote adapterRemote;
	
	@Autowired
	private InvHeaderRepository invHeaderRepository;

	@Autowired
    private AccessLogRepository accessLogRepository;
	
	@Transactional
    @Cacheable(value = "invheader", key = "'invheader'.concat(#invNum.toString())")
	public String query(String invNum, String type, String invDate) throws Exception{
		log.info(">>>>>>>> execute query. not cache");
		Optional<InvHeader> o = invHeaderRepository.findById(invNum);
		if (o.isPresent()) {
			InvHeader entity = invHeaderRepository.findById(invNum).get();
			log.info(">>>>>>>>> {} is exist in db",invNum);
			log(invNum, type, invDate, entity.getJsonStr());
			return entity.getJsonStr();

		}
		
		String apiResult = adapterRemote.qryInvHeader(invNum, type, invDate);
		log.info(">>>>>>>>> call adapter service(qryInvHeader), invNum:{} type:{} invDate:{}",invNum, type, invDate);
		persist(invNum, type, invDate, apiResult);
		log(invNum, type, invDate, apiResult);
		return apiResult;
	}
	
	public void persist(String invNum, String type, String invDate, String apiResult) throws IllegalAccessException, InvocationTargetException {

		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> result = springParser.parseMap(apiResult);
		log.info("result : {}",result);
		
		InvHeader entity = new InvHeader();
		BeanUtils.populate(entity, result);
		entity.setInvNum(invNum);	//key一定要設, 不然有error, persist會exception
		entity.setJsonStr(apiResult);
		log.info("entity : {}",entity);
		invHeaderRepository.save(entity);
	}
	
	public void log(String invNum, String type, String invDate, String apiResult) throws Exception {
        AccessLog accessLog = new AccessLog();
        accessLog.setApiName("qryInvHeader");
        accessLog.setInvokeTime( new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        accessLog.setInput(invNum+","+type+","+invDate);
        accessLog.setOutput(apiResult);
        accessLogRepository.save(accessLog);
		
	}
}
