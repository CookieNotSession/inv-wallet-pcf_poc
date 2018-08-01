package com.test.inv.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.test.inv.adapter.obj.MOFParamEnum;
import com.test.inv.adapter.util.UUIDUtil;
import com.test.inv.util.HttpAPIService;
import com.test.inv.util.HttpResult;

@RefreshScope
@Service
public class QryInvDetailService extends ServiceObject {
	
	@Value("${mof.appID}")
	private String appID;
	
	@Value("${mof.serverUrl}")
	private String serverUrl;	
	
	@Value("${mof.api.qryInvDetail}")
	private String apiUrl;	
	
    @Resource
    private HttpAPIService httpAPIService;
	
	public String doAction(Map<String, Object> map) throws Exception {
		
		String url = serverUrl + apiUrl;
		map.put(MOFParamEnum.versionQryInvDetail.getName(), 
				MOFParamEnum.versionQryInvDetail.getValue());
		map.put(MOFParamEnum.actionQryInvDetail.getName(), 
				MOFParamEnum.actionQryInvDetail.getValue());
		
		map.put(MOFParamEnum.generation.getName(), 
				"V2");
		map.put(MOFParamEnum.appID.getName(), 
				appID);
		map.put(MOFParamEnum.UUID.getName(), 
				UUIDUtil.generate());
		map.put(MOFParamEnum.randomNumber.getName(), 
				"0000");
		
		
		logger.info("Http request => {}",map);
        HttpResult httpResult = httpAPIService.doPost(url,map);
        logger.info("Http result => code:[{}] ,body:[{}]",httpResult.getCode(),httpResult.getBody());
        return httpResult.getBody();
	}
	


}
