package com.practice.mypay.accountdetailsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/*
1.	In Traditional xml based approach in Spring all the bean definitions are defined in applicationContext.xml configuration file
	spring container uses this root context configuration to create "application context".
 
2.	But we are using java annotations imported from org.springframework.context.annotation
	@Configuration - class level annotation, which has @Bean annotations

3.	By default, the scope of beans created by spring container is "Singleton", which can be changed to "Prototype".
	A singleton bean - only once the bean is created by spring container during the life of the application

 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).
				select().
				apis(RequestHandlerSelectors.any()).
				paths(PathSelectors.regex("/accountsService/.*")).
				build().apiInfo(apiEndPointsInfo());
	}
	
	private ApiInfo apiEndPointsInfo() 
	{	
        return new ApiInfoBuilder().title("ACCOUNTS API")
            .description("Accounts related services swagger document"+"\n\n"+"Contact me: ")
            .contact(new Contact("Nikhil Thakur", "http://www.23rdtechguy.com", "nikhilprasadthakur@gmail.com"))
            //.license("Apache 2.0")
            //.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("v1")
            .build();
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
