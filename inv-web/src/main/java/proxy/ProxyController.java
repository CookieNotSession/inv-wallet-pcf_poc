package proxy;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProxyController {

  private static HashMap<String, String> _users;

  private String service1Server = "http://localhost:8080";
  private String service2Server = "http://localhost:8030";
  private String server = service1Server;

  private RestTemplate rest;
  private HttpHeaders headers;
  private HttpStatus status;

  static {
    _users = new HashMap<String, String>();
    // sample data
    _users.put("user1","{\"username\":\"user1\",\"password\":\"user1\",\"name\":\"User-1\",\"cardType\":\"3J0002\",\"cardNo\":\"/B27.PO2\",\"startDate\":\"2018/01/01\",\"endDate\":\"2018/07/22\",\"onlyWinningInv\":\"N\",\"cardEncrypt\":\"ieoasl2180\"}");
  }

  public ProxyController() {
    this.rest = new RestTemplate();
    this.headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Accept", "*/*");
  }

  @RequestMapping("/")
  public String index() {
  	return "Service Alive";
  }

  // 載具發票表頭查詢
  @RequestMapping("/carrierInvChk")
  public String carrierInvChk(
      @RequestParam(value="cardType", defaultValue="") String cardType,
      @RequestParam(value="cardNo", defaultValue="") String cardNo,
      @RequestParam(value="startDate", defaultValue="") String startDate,
      @RequestParam(value="endDate", defaultValue="") String endDate,
      @RequestParam(value="onlyWinningInv", defaultValue="N") String onlyWinningInv,
      @RequestParam(value="cardEncrypt", defaultValue="") String cardEncrypt
    ) {
    this.server = this.service1Server;
    return this.post("/inv-adapter/api/inv-query/carrierInvChk?cardType="+cardType+"&cardNo="+cardNo+"&startDate="+startDate+"&endDate="+endDate+"&onlyWinningInv="+onlyWinningInv+"&cardEncrypt="+cardEncrypt);
  }
 
  // 手機條碼歸戶載具查詢
  @RequestMapping("/qryCarrierAgg")
  public String carrierInvChk(
      @RequestParam(value="serial", defaultValue="") String serial,
      @RequestParam(value="cardNo", defaultValue="") String cardNo,
      @RequestParam(value="cardType", defaultValue="") String cardType,
      @RequestParam(value="cardEncrypt", defaultValue="") String cardEncrypt
    ) {
    this.server = this.service1Server;
    return this.post("/inv-adapter/api/inv-query/qryCarrierAgg?serial="+serial+"&cardNo="+cardNo+"&cardType="+cardType+"&cardEncrypt="+cardEncrypt);
  }

  // 查詢發票表頭
  @RequestMapping("/qryInvHeader")
  public String carrierInvChk(
      @RequestParam(value="invNum", defaultValue="") String invNum,
      @RequestParam(value="type", defaultValue="Barcode") String type,
      @RequestParam(value="invDate", defaultValue="") String invDate
    ) {
    this.server = this.service1Server;
    return this.post("/inv-adapter/api/inv-query/qryInvHeader?invNum="+invNum+"&type="+type+"&invDate="+invDate);
  }

  // 查詢中獎發票號碼清單
  @RequestMapping("/winningList")
  public String carrierInvChk(
      @RequestParam(value="invTerm", defaultValue="") String invTerm
    ) {
    this.server = this.service1Server;
    return this.get("/inv-adapter/api/inv-query/winningList/"+invTerm);
  }

  // 查詢發票明細
  @RequestMapping("/invDetail")
  public String invDetail(
      @RequestParam(value="name", defaultValue="") String name,
      @RequestParam(value="period", defaultValue="") String period
    ) {
    this.server = this.service2Server;
    return this.post("/api/invDetail?name="+name+"&period="+period);
  }

  // 讀取使用者資料
  @RequestMapping("/user/login")
  public String login(
      @RequestParam(value="username", defaultValue="") String username,
      @RequestParam(value="password", defaultValue="") String password
    ) {

    String rtl = "";
    if(username!=null && username.equals(password)) {
      rtl = _users.get(username);
    }

    return rtl;
  }

  // 註冊使用者資料
  @RequestMapping("/user/register")
  public String register(
      @RequestParam(value="username", defaultValue="") String username,
      @RequestParam(value="cardType", defaultValue="") String cardType,
      @RequestParam(value="cardNo", defaultValue="") String cardNo,
      @RequestParam(value="cardEncrypt", defaultValue="") String cardEncrypt
    ) {
    String rtl = "{\"username\":\""+username+"\",\"password\":\""+username+"\",\"name\":\""+username+"\",\"cardType\":\""+cardType+"\",\"cardNo\":\""+cardNo+"\",\"startDate\":\"2018/01/01\",\"endDate\":\"2018/07/22\",\"onlyWinningInv\":\"N\",\"cardEncrypt\":\""+cardEncrypt+"\"}";
    if(username != null && _users.get(username)==null) {
      _users.put(username, rtl);
    } else {
      rtl = "";
    }
    return rtl;
  }

  public String get(String uri) {
    HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
    this.setStatus(responseEntity.getStatusCode());
    return responseEntity.getBody();
  }

  public String post(String uri) {   
    HttpEntity<String> requestEntity = new HttpEntity<String>("{}", headers);
    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, String.class);
    this.setStatus(responseEntity.getStatusCode());
    return responseEntity.getBody();
  }

  public String post(String uri, String json) {   
    HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, String.class);
    this.setStatus(responseEntity.getStatusCode());
    return responseEntity.getBody();
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  } 

}
