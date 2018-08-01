package com.test.inv.server.configserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigServerApplication {

	//curl -X POST http://localhost:8020/actuator/bus-refresh => config refresh 
	//curl -X POST http://localhost:8007/actuator/refresh => spring boot refresh
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
