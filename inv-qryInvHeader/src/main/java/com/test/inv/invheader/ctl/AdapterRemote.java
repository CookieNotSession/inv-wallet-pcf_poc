package com.test.inv.invheader.ctl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${adapter.name}", fallback = AdapterRemoteHystrix.class)
public interface AdapterRemote {

	@PostMapping("${adapter.qryInvHeader}")
	public String qryInvHeader(@RequestParam("invNum") String invNum, @RequestParam("type") String type,
			@RequestParam("invDate") String invDate);

}
