package com.test.inv.walletapi.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.test.inv.util.HttpAPIService;
import com.test.inv.util.HttpResult;
import com.test.inv.walletapi.entity.UserProfile;
import com.test.inv.walletapi.mock.UserProfileMockDao;
import com.test.inv.walletapi.obj.AdapterParamEnum;
import com.test.inv.walletapi.util.DateUtil;

@Service
public class InvDetailService extends ServiceObject{

	@Value("${adapter.serverUrl}")
	private String serverUrl;	

	@Value("${adapter.carrierInvChk}")
	private String apiUrl;	

	@Autowired
	private UserProfileMockDao userProfileDao;

	@Resource
	private HttpAPIService httpAPIService;

	@Autowired
	private RestTemplate rest;

	public String doAction(String name, String date) throws Exception {
		UserProfile profile = userProfileDao.findByName(name);

		String[] period = DateUtil.getFirstLastDate(
				DateUtil.parseDate(date, "yyyyMM"),"yyyy/MM/dd");


		String url = serverUrl + apiUrl;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(AdapterParamEnum.cardType.getName(),
				profile.getCardType());
		map.put(AdapterParamEnum.cardNo.getName(),
				profile.getCardNo());
		map.put(AdapterParamEnum.startDate.getName(),
				period[0]);		
		map.put(AdapterParamEnum.endDate.getName(),
				period[1]);	
		map.put(AdapterParamEnum.onlyWinningInv.getName(),
				"N");
		map.put(AdapterParamEnum.cardEncrypt.getName(),
				profile.getCardEncrypt());


		logger.info("Http request => {}",map);
		HttpResult httpResult = httpAPIService.doPost(url,map);
		logger.info("Http result => code:[{}] ,body:[{}]",httpResult.getCode(),httpResult.getBody());
		return httpResult.getBody();
	}

	public Object invokeService(String name, String date) throws Exception {
		UserProfile profile = userProfileDao.findByName(name);

		String[] period = DateUtil.getFirstLastDate(
				DateUtil.parseDate(date, "yyyyMM"),"yyyy/MM/dd");


		String url = serverUrl + apiUrl;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map<String, String> map = new HashMap<String, String>();
		//map.put("Content-Type", "application/json");
		headers.setAll(map);


		Map<String, Object> reqPayload = new HashMap<String, Object>();
		reqPayload.put(AdapterParamEnum.cardType.getName(),
				profile.getCardType());
		reqPayload.put(AdapterParamEnum.cardNo.getName(),
				profile.getCardNo());
		reqPayload.put(AdapterParamEnum.startDate.getName(),
				period[0]);		
		reqPayload.put(AdapterParamEnum.endDate.getName(),
				period[1]);	
		reqPayload.put(AdapterParamEnum.onlyWinningInv.getName(),
				"N");
		reqPayload.put(AdapterParamEnum.cardEncrypt.getName(),
				profile.getCardEncrypt());

		HttpEntity<?> request = new HttpEntity<>(reqPayload, headers);

		logger.info("Http url => {}",url);

		logger.info("Http request => {}",reqPayload);
		Object result 
		= rest.postForEntity(url, request, Object.class);


		logger.info("Http result => {}",result.getClass().getName());
		logger.info("Http result => {}",result);
		return result;
	}

}
