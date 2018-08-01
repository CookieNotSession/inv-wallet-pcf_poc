package com.test.inv.feign.ctl;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdapterRemoteHystrix implements AdapterRemote{
	
	
    @Override
    public String winningList(@PathVariable("invTerm") String invTerm) {
		log.info("errorQryWinningList invTerm : "+ invTerm);
		String httpResult = "{\"message\":\"Backend Server not available\"}";
		return httpResult;	}


}
