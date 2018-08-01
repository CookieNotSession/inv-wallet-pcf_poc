package com.test.inv.walletapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan({"com.test.inv"})
@EnableJpaRepositories("com.test.inv.walletdb.dao")
@EntityScan("com.test.inv.walletdb.entity")  

@EnableDiscoveryClient
@SpringBootApplication
public class WalletApiApplication {

	@Value("${env.server}")
	public String env = "";	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WalletApiApplication.class, args);
	}
	
	@Bean
	public Docket createQueryRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("Query API")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.test.inv.walletapi.ctl"))
				.paths(PathSelectors.any())
				.build();
	}
	
    @SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("["+env+"] swagger wallet RESTful APIs")
                .description("test swagger wallet RESTful APIs ....... ")
                .termsOfServiceUrl("http://www.test.com/")
                .contact("richardkang_tw@msn.com")
                .version("1.0")
                .build();
    }
}
