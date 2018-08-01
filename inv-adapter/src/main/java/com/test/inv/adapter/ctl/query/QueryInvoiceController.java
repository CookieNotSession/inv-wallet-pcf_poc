package com.test.inv.adapter.ctl.query;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.inv.adapter.obj.MOFParamEnum;
import com.test.inv.service.CarrierInvChkService;
import com.test.inv.service.CarrierInvDetailService;
import com.test.inv.service.QryCarrierAggService;
import com.test.inv.service.QryInvDetailService;
import com.test.inv.service.QryInvHeaderService;
import com.test.inv.service.QryWinningListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "inv-query")
@RestController
@RequestMapping("/api/inv-query")
public class QueryInvoiceController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired 
	private QryWinningListService qryWinningListService;

	@Autowired 
	private QryInvHeaderService qryInvHeaderService;
	
	@Autowired
	private CarrierInvChkService carrierInvChkService;
	
	@Autowired
	private QryCarrierAggService qryCarrierAggService;
	
	@Autowired
	private QryInvDetailService qryInvDetailService;
	
	@Autowired
	private CarrierInvDetailService carrierInvDetailService;
	
	@GetMapping(value = "/winningList/{invTerm}")
	@ApiOperation(value = "查詢中獎發票號碼清單",notes = "/PB2CAPIVAN/invapp/InvApp")
	public Map<String, Object> qryWinningList(
			@ApiParam(name = "invTerm", value = "查詢開獎期別，年分為民國年，月份必須為雙數月. 格式(yyyMM)", defaultValue = "10104")
			@PathVariable String invTerm) throws Throwable {
		logger.info("invTerm : "+ invTerm);
		String httpResult = qryWinningListService.doAction(invTerm);
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> result = springParser.parseMap(httpResult);
		return result;
		
	}
	
	@PostMapping("/qryInvHeader")
	@ApiOperation(value = "查詢發票表頭",notes = "/PB2CAPIVAN/invapp/InvApp")
	public Map<String, Object> qryInvHeader(
			@ApiParam(name = "invNum", value = "發票號碼", defaultValue = "DR13979447")
			@RequestParam(value = "invNum") String invNum,
			@ApiParam(name = "type", value = "條碼別. 值(QRCode/Barcode)", defaultValue = "Barcode")
			@RequestParam(value = "type") String type,
			@ApiParam(name = "invDate", value = "發票日期. 格式(yyyy/MM/dd)", defaultValue = "2018/06/19")
			@RequestParam(value = "invDate") String invDate) throws Exception {
		logger.info("invNum:{},type:{},invDate:{} ",invNum,type,invDate);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MOFParamEnum.invType.getName(), 
				type);		
		map.put(MOFParamEnum.invNum.getName(), 
				invNum);	
		map.put(MOFParamEnum.invDate.getName(), 
				invDate);	
		String httpResult = qryInvHeaderService.doAction(map);
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> result = springParser.parseMap(httpResult);
		return result;
	}
	@PostMapping("/qryInvDetail")
	@ApiOperation(value = "查詢發票明細",notes = "/PB2CAPIVAN/invapp/InvApp")
	public Map<String, Object> qryInvDetail(
			@ApiParam(name = "invNum", value = "發票號碼", defaultValue = "BK07893760")
			@RequestParam(value = "invNum") String invNum,
			@ApiParam(name = "type", value = "條碼別. 值(QRCode/Barcode)", defaultValue = "Barcode")
			@RequestParam(value = "type") String type,			
			@ApiParam(name = "invTerm", value = "發票期別. 格式(yyyMM), Type 為 Barcode 時為必填", defaultValue = "10704")
			@RequestParam(value = "invTerm") String invTerm,
			@ApiParam(name = "invDate", value = "發票日期. 格式(yyyy/MM/dd)", defaultValue = "2018/03/07")
			@RequestParam(value = "invDate") String invDate,
			@ApiParam(name = "encrypt", value = "發票檢驗碼. Type為QRCode 時為必填",defaultValue = "")
			@RequestParam(value = "encrypt", required = false) String encrypt,
			@ApiParam(name = "sellerID", value = "商家統編. Type為QRCode 時為必填" ,defaultValue = "")
			@RequestParam(value = "sellerID", required = false) String sellerID			
		) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MOFParamEnum.invType.getName(), 
				type);		
		map.put(MOFParamEnum.invNum.getName(), 
				invNum);	
		map.put(MOFParamEnum.invTerm.getName(), 
				invTerm);	
		map.put(MOFParamEnum.invDate.getName(), 
				invDate);	
		map.put(MOFParamEnum.encrypt.getName(), 
				StringUtils.isBlank(encrypt)?"":encrypt);	
		map.put(MOFParamEnum.sellerID.getName(), 
				StringUtils.isBlank(sellerID)?"":sellerID);	
		
		String httpResult = qryInvDetailService.doAction(map);
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> result = springParser.parseMap(httpResult);
		return result;
		
	}
	
	@PostMapping("/carrierInvChk")
	@ApiOperation(value = "載具發票表頭查詢",notes = "/PB2CAPIVAN/invServ/InvServ")
	public Map<String, Object> carrierInvChk(
			@ApiParam(name = "cardType", value = "卡別. 值(3J0002:手機條碼)", defaultValue = "3J0002")
			@RequestParam(value = "cardType") String cardType,
			@ApiParam(name = "cardNo", value = "手機條碼/卡片(載具)隱碼", defaultValue = "/B27.PO2")
			@RequestParam(value = "cardNo") String cardNo,
			@ApiParam(name = "startDate", value = "查詢起始時間", defaultValue = "2018/03/01")
			@RequestParam(value = "startDate") String startDate,
			@ApiParam(name = "endDate", value = "查詢結束時間", defaultValue = "2018/03/30")
			@RequestParam(value = "endDate") String endDate,
			@ApiParam(name = "onlyWinningInv", value = "僅回傳中獎資訊 (Y/N)", defaultValue = "N")
			@RequestParam(value = "onlyWinningInv") String onlyWinningInv,	
			@ApiParam(name = "cardEncrypt", value = "手機條碼驗證碼/卡片(載 具)驗證碼", defaultValue = "ieoasl2180")
			@RequestParam(value = "cardEncrypt") String cardEncrypt	
			) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MOFParamEnum.cardType.getName(), 
				cardType);		
		map.put(MOFParamEnum.cardNo.getName(), 
				cardNo);	
		map.put(MOFParamEnum.startDate.getName(), 
				startDate);	
		map.put(MOFParamEnum.endDate.getName(), 
				endDate);	
		map.put(MOFParamEnum.onlyWinningInv.getName(), 
				onlyWinningInv);	
		map.put(MOFParamEnum.cardEncrypt.getName(), 
				cardEncrypt);	
		String httpResult = carrierInvChkService.doAction(map);
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> result = springParser.parseMap(httpResult);
		return result;
		
	}
	
	@PostMapping("/carrierInvDetail")
	@ApiOperation(value = "載具發票明細查詢",notes = "/PB2CAPIVAN/invServ/InvServ")
	public Map<String, Object> carrierInvDetail(
			@ApiParam(name = "cardType", value = "卡別. 值(3J0002:手機條碼)", defaultValue = "3J0002")
			@RequestParam(value = "cardType") String cardType,
			@ApiParam(name = "cardNo", value = "手機條碼/卡片(載具)隱碼", defaultValue = "/B27.PO2")
			@RequestParam(value = "cardNo") String cardNo,
			@ApiParam(name = "invNum", value = "發票號碼", defaultValue = "BK07893760")
			@RequestParam(value = "invNum") String invNum,
			@ApiParam(name = "invDate", value = "發票日期. 格式(yyyy/MM/dd)", defaultValue = "2018/03/07")
			@RequestParam(value = "invDate") String invDate,
			@ApiParam(name = "cardEncrypt", value = "手機條碼驗證碼/卡片(載 具)驗證碼", defaultValue = "ieoasl2180")
			@RequestParam(value = "cardEncrypt") String cardEncrypt	
			) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MOFParamEnum.cardType.getName(), 
				cardType);		
		map.put(MOFParamEnum.cardNo.getName(), 
				cardNo);	
		map.put(MOFParamEnum.invNum.getName(), 
				invNum);	
		map.put(MOFParamEnum.invDate.getName(), 
				invDate);	
		map.put(MOFParamEnum.cardEncrypt.getName(), 
				cardEncrypt);	
		String httpResult = carrierInvDetailService.doAction(map);
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> result = springParser.parseMap(httpResult);
		return result;
		
	}
	
	@PostMapping("/qryCarrierAgg")
	@ApiOperation(value = "手機條碼歸戶載具查詢",notes = "/PB2CAPIVAN/Carrier/Aggregate")
	public Map<String, Object> qryCarrierAgg(
			@ApiParam(name = "serial", value = "傳送時的序號(10位數字)", defaultValue = "0000000001")
			@RequestParam(value = "serial") String serial,
			@ApiParam(name = "cardType", value = "卡別. 值(3J0002:手機條碼)", defaultValue = "3J0002")
			@RequestParam(value = "cardType") String cardType,
			@ApiParam(name = "cardNo", value = "手機條碼/卡片(載具)隱碼", defaultValue = "/B27.PO2")
			@RequestParam(value = "cardNo") String cardNo,
			@ApiParam(name = "cardEncrypt", value = "手機條碼驗證碼/卡片(載 具)驗證碼", defaultValue = "ieoasl2180")
			@RequestParam(value = "cardEncrypt") String cardEncrypt	
			) throws Exception {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put(MOFParamEnum.cardType.getName(), 
				cardType);		
		map.put(MOFParamEnum.cardNo.getName(), 
				cardNo);	
		map.put(MOFParamEnum.serial.getName(), 
				serial);	
		map.put(MOFParamEnum.cardEncrypt.getName(), 
				cardEncrypt);	
		String httpResult = qryCarrierAggService.doAction(map);
		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> result = springParser.parseMap(httpResult);
		return result;
		
	}
	
	

	
}
