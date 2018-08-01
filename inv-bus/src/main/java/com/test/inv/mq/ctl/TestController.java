package com.test.inv.mq.ctl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${app.text}")
	private String appText;
	
	@GetMapping(value = "/hello")
	@ApiOperation(value = "測試Config設定，curl -X POST http://localhost:8007/actuator/refresh")
	@RefreshScope
	/**
	 * curl -X POST http://localhost:8007/actuator/refresh  -d {} -H "Content-Type: application/json"
	 */
	public String hello() throws Throwable {
		return appText;
		
	}
	
}
