package com.test.inv.winning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringCloudApplication
@EnableJpaRepositories("com.test.inv.repo")
@EntityScan("com.test.inv.entity")
public class InvMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvMemberApplication.class, args);
	}
	
	@Bean
	public Docket createQueryRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("Query API")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.test.inv.winning.ctl"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger RESTful APIs")
                .description("test swagger RESTful APIs ....... ")
                .termsOfServiceUrl("http://www.test.com/")
                .version("1.0")
                .build();
    }
}
