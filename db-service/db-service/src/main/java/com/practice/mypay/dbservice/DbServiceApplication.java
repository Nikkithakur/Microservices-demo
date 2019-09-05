package com.practice.mypay.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
Entry point, from here the application starts execution
@EnableJpaRepositories not required, it is to point spring application towards repositories
*/
//@EnableJpaRepositories(basePackages = "com.practice.mypay.dbservice.repo")

@EnableEurekaClient
@SpringBootApplication
public class DbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}

}
