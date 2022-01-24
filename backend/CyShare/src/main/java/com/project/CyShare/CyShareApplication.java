package com.project.CyShare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Hugo Alvarez Valdivia
 * @author Bhuwan Joshi
 */

@SpringBootApplication
@EnableSwagger2
public class CyShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyShareApplication.class, args);
	}

	/**
	 * Configuration for Swagger
	 * @return API Documentation
	 */
	@Bean
	public Docket getAPIDocs()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.project.CyShare.controller"))
				.paths(PathSelectors.any())
				.build();
	}

}
