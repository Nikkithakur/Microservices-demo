package com.practice.mypay.paymentsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).
				select().
				apis(RequestHandlerSelectors.any()).
				paths(PathSelectors.regex("/paymentsService/.*")).
				build();
		}
	
	// Below line can also be used to sppecify swagger documentation is needed only for class annotated with rest controller
		/*
		@Bean
		public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
	    .select()
	    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	    .paths(PathSelectors.any())
	    .build();
		}
		*/
}
