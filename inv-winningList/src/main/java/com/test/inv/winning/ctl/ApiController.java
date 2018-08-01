package com.test.inv.winning.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.inv.winning.service.WinningListService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RefreshScope
@Slf4j
public class ApiController {

	@Value("${env.server}")
	public String env = "";	

	@Autowired
	private WinningListService winningListService;
	
	@GetMapping(value = "/winningList/{invTerm}")
	@ApiOperation(value = "查詢中獎發票號碼清單")
	public String inv(
			@ApiParam(name = "invTerm", value = "查詢開獎期別，年分為民國年，月份必須為雙數月. 格式(yyyMM)", defaultValue = "10104")
			@PathVariable String invTerm) {	
		log.info(">>>>>>>> env:[{}], query interm:[{}]",env, invTerm);
		try {
			return winningListService.query(invTerm);
		} catch (Exception e) {
			String err = "{\"message\":\""+e.toString()+"\"}";
			return err;
		}
	}
}
