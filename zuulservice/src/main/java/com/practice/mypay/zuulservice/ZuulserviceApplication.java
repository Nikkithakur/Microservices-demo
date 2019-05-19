package com.practice.mypay.zuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/*
For routes
https://localhost:65354/actuator/routes 
and
management.endpoints.web.exposure.include: "*"
should be included in yaml
 */

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulserviceApplication.class, args);
	}

}
