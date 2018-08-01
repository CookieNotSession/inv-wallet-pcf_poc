package com.test.inv.feign.ctl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class ApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdapterRemote adapterRemote;

	@GetMapping(value = "/winningList/{invTerm}")
	@ApiOperation(value = "查詢中獎發票號碼清單")
	public String inv(
			@ApiParam(name = "invTerm", value = "查詢開獎期別，年分為民國年，月份必須為雙數月. 格式(yyyMM)", defaultValue = "10104")
			@PathVariable String invTerm) {	
		return adapterRemote.winningList(invTerm);
	

	}
}
