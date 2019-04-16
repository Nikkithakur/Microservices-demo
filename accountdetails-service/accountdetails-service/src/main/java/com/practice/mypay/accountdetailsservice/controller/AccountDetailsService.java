package com.practice.mypay.accountdetailsservice.controller;


import com.practice.mypay.accountdetailsservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("rest/accountsService")
public class AccountDetailsService {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getDetails/{phoneNumber}")
    public Customer getAccountDetails(@PathVariable("phoneNumber") final  String phoneNumber)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        String url="http://db-service/rest/db/"+phoneNumber;

       ResponseEntity responseEntity= restTemplate.exchange(url,HttpMethod.GET,entity,Customer.class);
       return (Customer) responseEntity.getBody();

    }



}
