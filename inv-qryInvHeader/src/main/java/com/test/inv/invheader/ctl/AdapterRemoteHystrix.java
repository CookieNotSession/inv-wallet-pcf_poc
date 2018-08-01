package com.test.inv.invheader.ctl;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdapterRemoteHystrix implements AdapterRemote {

	@Override
	public String qryInvHeader(@RequestParam("invNum") String invNum, @RequestParam("type") String type,
			@RequestParam("invDate") String invDate) {
		log.info("errorQryInvHeader invNum:{}, type:{}, invDate:{} ", type, invNum, invDate);
		String httpResult = "{\"message\":\"Backend Server not available\"}";
		return httpResult;
	}

}
