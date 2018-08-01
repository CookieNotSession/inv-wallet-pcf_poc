package com.test.inv.hystrix.ctl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${adapter.serverUrl}")
	private String serverUrl;	

	@Value("${adapter.winningList}")
	private String apiUrl;	

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/winningList/{invTerm}")
	@ApiOperation(value = "查詢中獎發票號碼清單")
    @HystrixCommand(fallbackMethod = "errorQryWinningList"
    ,ignoreExceptions = ArithmeticException.class)	
	public String inv(
			@ApiParam(name = "invTerm", value = "查詢開獎期別，年分為民國年，月份必須為雙數月. 格式(yyyMM)", defaultValue = "10104")
			@PathVariable String invTerm) {
		String baseUrl = serverUrl+apiUrl+"/"+invTerm;
		log.info("baseUrl:{}",baseUrl);
		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
		logger.info("Http result => [{}] , [{}]",response.getStatusCode(), response.getBody());

		if (response.getStatusCode()==HttpStatus.OK) {
			return response.getBody();
		}else {
			return response.getBody();
		}		

	}
	
	public String errorQryWinningList(String invTerm,Throwable throwable) {
		log.info("errorQryWinningList invTerm : "+ invTerm);
		log.error("errorQryWinningList, e ", throwable);
		String httpResult = "{\"message\":\"Backend Server not available\"}";
		return httpResult;
		
	}

}
