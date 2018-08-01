package com.test.inv.walletapi.ctl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.inv.walletapi.service.InvDetailService;
import com.test.inv.walletapi.service.QryWinningListService;
import com.test.inv.walletapi.util.DateUtil;
import com.test.inv.walletdb.dao.AccessLogRepository;
import com.test.inv.walletdb.entity.AccessLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "query")
@RestController
@RequestMapping("/api")
public class ApiController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private QryWinningListService qryWinningListService;
	
	@Autowired
	private  InvDetailService invDetailService;
	
    @Autowired(required=true)
    @Qualifier("accessLogRepository")
    private AccessLogRepository accessLogRepository;
    
	@GetMapping(value = "/winningList/{invTerm}")
	@ApiOperation(value = "查詢中獎發票號碼清單")
	@Transactional
	public Map<String, Object> qryWinningList(
			@ApiParam(name = "invTerm", value = "查詢開獎期別，年分為民國年，月份必須為雙數月. 格式(yyyMM)", defaultValue = "10104")
			@PathVariable String invTerm) throws Throwable {
		
		logger.info("invTerm : "+ invTerm);
		//String httpResult = "{\"formSubmitter\":\"15257\",\"notifierDispatcher\":\"15257\",\"managerDispatcher\":\"15257\",\"productManager\":\"15257\",\"nextStageName\":\"UnitReviewer\",\"nextStageAuthorizer\":\"15257\"}";//qryWinningListService.doAction(invTerm);
		Map<String, Object> result 
			= (LinkedHashMap)qryWinningListService.invokeService(invTerm);
//		JsonParser springParser = JsonParserFactory.getJsonParser();
//		Map<String, Object> result = springParser.parseMap(httpResult);
		
        AccessLog accessLog = new AccessLog();
        accessLog.setApiName("winningList");
        accessLog.setInvokeTime(DateUtil.getCurrTime());
        accessLog.setInput(invTerm);
        accessLog.setOutput(result.toString());
        accessLogRepository.save(accessLog);
	    
		return result;
		
	}
	@PostMapping("/invDetail")
	@ApiOperation(value = "查詢發票明細")
	@Transactional
	public Map<String, Object> invDetail(
			@ApiParam(name = "name", value = "name", defaultValue = "richard")
			@RequestParam(value = "name") String name,
			@ApiParam(name = "period", value = "查詢時間. 格式(yyyyMM)", defaultValue = "201803")
			@RequestParam(value = "period") String period) throws Exception {

		Map<String, Object> result  
			= (LinkedHashMap) invDetailService.invokeService(name, period);
//		JsonParser springParser = JsonParserFactory.getJsonParser();
//		Map<String, Object> result = springParser.parseMap(httpResult);
//		
        AccessLog accessLog = new AccessLog();
        accessLog.setApiName("invDetail");
        accessLog.setInvokeTime(DateUtil.getCurrTime());
        accessLog.setInput(name+","+period);
        accessLog.setOutput(result.toString());
        accessLogRepository.save(accessLog);
        
		return result;
		
	}
	
	
	
}
