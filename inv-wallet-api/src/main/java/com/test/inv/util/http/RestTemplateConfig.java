package com.test.inv.util.http;

import org.apache.http.HttpHost;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;

@Configuration
public class RestTemplateConfig {

	private final int TIMEOUT = 10000;

	//@Value("${proxy-username}")
	private String proxyUsername;
	//@Value("${proxy-password}")
	private String proxyPassword;
	//@Value("${proxy-host}")
	private String proxyHost;
	//@Value("${proxy-prot}")
	private int proxyPort;  
	//@Value("${proxy-flag}")
	private boolean proxyFlag = false;

	//@Bean("restTemplateSSL")
	public RestTemplate restTemplateSSL(RestTemplateBuilder builder) throws Exception {
		TrustStrategy acceptingTrustStrategy =
				(X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
				.loadTrustMaterial(null, acceptingTrustStrategy)
				.build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		
		CloseableHttpClient httpClient = null;
		if (proxyFlag) {
			//Handle Proxy
			HttpHost proxy = new HttpHost(proxyHost,proxyPort);
			httpClient = HttpClients.custom()
					.setProxy(proxy).setSSLSocketFactory(csf)
					.build();    	
		}else {
			httpClient = HttpClients.custom()
					.setSSLSocketFactory(csf)
					.build();   	
		}

		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(TIMEOUT);
		requestFactory.setReadTimeout(TIMEOUT);
		requestFactory.setConnectionRequestTimeout(TIMEOUT);
		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		if (proxyFlag) { 
			restTemplate.getInterceptors().add(
					new BasicAuthorizationInterceptor(proxyUsername,proxyPassword));
		}

		return restTemplate;
	}
}