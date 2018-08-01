package com.test.inv.invheader.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.inv.invheader.service.QryInvHeaderService;

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
	private QryInvHeaderService qryInvHeaderService;

	@PostMapping(value = "/qryInvHeader")
	@ApiOperation(value = "查詢發票表頭")
	public String inv(
			@ApiParam(name = "invNum", value = "發票號碼", defaultValue = "EX94811139") @RequestParam(value = "invNum") String invNum,
			@ApiParam(name = "type", value = "發票查詢時使用的條碼別", defaultValue = "Barcode") @RequestParam(value = "type") String type,
			@ApiParam(name = "invDate", value = "發票日期(yyyy/MM/dd)", defaultValue = "2018/07/23") @RequestParam(value = "invDate") String invDate) {
		log.info(">>>>>>>> env:[{}], query interm:[invNum:{}, type:{}, invDate:{}]", env, invNum, type, invDate);
		try {
			return qryInvHeaderService.query(invNum, type, invDate);
		} catch (Exception e) {
			String err = "{\"message\":\"" + e.toString() + "\"}";
			return err;
		}
	}
}
