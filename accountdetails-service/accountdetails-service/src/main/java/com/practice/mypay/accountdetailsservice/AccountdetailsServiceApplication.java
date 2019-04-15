package com.practice.mypay.accountdetailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AccountdetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountdetailsServiceApplication.class, args);
	}

}
