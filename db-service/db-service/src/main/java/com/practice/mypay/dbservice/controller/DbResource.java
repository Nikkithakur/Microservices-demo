package com.practice.mypay.dbservice.controller;

import com.practice.mypay.dbservice.exceptions.PaymentException;
import com.practice.mypay.dbservice.model.Customer;
import com.practice.mypay.dbservice.model.Wallet;
import com.practice.mypay.dbservice.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/*
@RestController species that all the mappings will produce json response, @ResponseBody is not required
 */
@RestController
@RequestMapping("rest/db")
public class DbResource {

    @Autowired
    AccountRepository accountRepository;

    /*
        consumes = "application/json",produces = "application/json" Not required, but if added
        while calling the endpoint consumes value should be sent as header
     */

    @PostMapping(value="/createAccount",consumes = "application/json",produces = "application/json")
    public Customer createAccount(@RequestBody Customer customer)
    {
       return accountRepository.save(customer);
    }

    @GetMapping(value = "/{phoneNumber}",consumes = "application/json",produces = "application/json")
    public Customer getAccountDetailsByPhoneNumber(@PathVariable("phoneNumber") final String phoneNumber)
    {
        return accountRepository.findCustomerByPhoneNumber(phoneNumber);
    }

    @GetMapping(value = "/{phoneNumber}/transactions",consumes = "application/json",produces = "application/json")
    public List getTransactionsListByPhoneNumber(@PathVariable("phoneNumber") final String phoneNumber)
    {
        return accountRepository.findCustomerByPhoneNumber(phoneNumber).getWallet().getTransactions();
    }
}
