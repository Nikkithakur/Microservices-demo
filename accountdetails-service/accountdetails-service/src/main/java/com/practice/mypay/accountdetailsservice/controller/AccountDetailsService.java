package com.practice.mypay.accountdetailsservice.controller;


import com.practice.mypay.accountdetailsservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

@RestController
public class AccountDetailsService {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value="accountsService/getDetails/{phoneNumber}",produces=MediaTypes.HAL_JSON_VALUE)
    public Customer getAccountDetails(@PathVariable("phoneNumber") final  String phoneNumber)
    {
        Customer customer;
    	String url="http://db-service/db/"+phoneNumber;
        customer = restTemplate.getForObject(url, Customer.class);
        Link selfLink = ControllerLinkBuilder.
        				linkTo(AccountDetailsService.class).
        				slash("accountsService/getDetails/"+phoneNumber).       				
        				withSelfRel();
        customer.add(selfLink);
        return customer;
    }
    
    @PostMapping(value="accountsService/addAccountDetails")
    public Customer addAccountDetails(@RequestBody Customer customer)
    {
    	String url="http://db-service/db/createAccount";
    	return restTemplate.postForObject(url, customer, Customer.class);
    }
    
    @GetMapping(value="accountsService/{phoneNumber}/transactions")
    public List addAccountDetails(@PathVariable("phoneNumber") final String phoneNumber)
    {
    	String url="http://db-service/db/"+phoneNumber+"/"+"transactions";
    	List list= restTemplate.getForObject(url,List.class);
    	Link selfLink = ControllerLinkBuilder.
				linkTo(AccountDetailsService.class).
				slash("accountsService/"+phoneNumber+"/transactions").       				
				withSelfRel();
    	list.add(selfLink);
    	return list;
    }



}
