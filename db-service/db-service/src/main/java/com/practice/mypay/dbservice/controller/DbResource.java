package com.practice.mypay.dbservice.controller;

import com.practice.mypay.dbservice.model.Customer;
import com.practice.mypay.dbservice.model.Transactions;

import com.practice.mypay.dbservice.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/*
@RestController species that all the mappings will produce json response, @ResponseBody is not required

beneficiary - destination
benefactor - source
 */
@RestController
public class DbResource {

    @Autowired
    AccountRepository accountRepository;

    /*
        consumes = "application/json",produces = "application/json" Not required, but if added
        while calling the endpoint consumes value should be sent as header
     */

    @PostMapping(value="db/createAccount")
    public Customer createAccount(@RequestBody Customer customer)
    {
       return accountRepository.save(customer);
    }

    @GetMapping(value = "db/{phoneNumber}")
    public Customer getAccountDetailsByPhoneNumber(@PathVariable("phoneNumber") final String phoneNumber)
    {
        return accountRepository.findCustomerByPhoneNumber(phoneNumber);
    }

    @GetMapping(value = "db/{phoneNumber}/transactions")
    public List<Transactions> getTransactionsListByPhoneNumber(@PathVariable("phoneNumber") final String phoneNumber)
    {
        return accountRepository.findCustomerByPhoneNumber(phoneNumber).getWallet().getTransactions();
    }


    @GetMapping(value = "db/makePayment/{number1}/{number2}/{amount}")
    public Customer makePayment(@PathVariable("number1") final String number1,@PathVariable("number2") final String number2,@PathVariable("amount") final BigDecimal transferAmount)
    {
        Customer benefactor,beneficiary;
        BigDecimal credit, debit;
        Transactions txn1=new Transactions();
        Transactions txn2=new Transactions();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        benefactor=accountRepository.findCustomerByPhoneNumber(number1);
        beneficiary=accountRepository.findCustomerByPhoneNumber(number2);


        debit=benefactor.getWallet().getBalance().subtract(transferAmount);
        benefactor.getWallet().setBalance(debit);
        txn1.setTransactionMsg(timestamp+" "+transferAmount+" has been sent to "+number2);
        benefactor.getWallet().getTransactions().add(txn1);

        credit=beneficiary.getWallet().getBalance().add(transferAmount);
        beneficiary.getWallet().setBalance(credit);
        txn2.setTransactionMsg(timestamp+"... Rs "+transferAmount+" has been sent to you by "+number1);
        beneficiary.getWallet().getTransactions().add(txn2);

        accountRepository.save(beneficiary);
        return accountRepository.save(benefactor);
    }
}
