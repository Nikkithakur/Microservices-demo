package com.practice.mypay.accountdetailsservice.controller;


import com.practice.mypay.accountdetailsservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import javax.annotation.PostConstruct;

@RestController
public class AccountDetailsService {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("accountsService/getDetails/{phoneNumber}")
    public Customer getAccountDetails(@PathVariable("phoneNumber") final  String phoneNumber)
    {
       String url="http://db-service/db/"+phoneNumber;
       return  restTemplate.getForObject(url, Customer.class);
    }
    
    @PostMapping(value="accountsService/addAccountDetails")
    public Customer addAccountDetails(@RequestBody Customer customer)
    {
    	String url="http://db-service/db/createAccount";
    	return restTemplate.postForObject(url, customer, Customer.class);
    }



}
