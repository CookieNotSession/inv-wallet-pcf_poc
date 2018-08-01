package com.test.inv.winning.ctl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name= "${adapter.name}", fallback = AdapterRemoteHystrix.class)
public interface AdapterRemote {

	@RequestMapping("${adapter.winningList}"+"/{invTerm}")
    public String winningList(@PathVariable("invTerm") String invTerm);

}
