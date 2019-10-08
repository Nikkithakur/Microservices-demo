package com.practice.mypay.accountdetailsservice.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class Wallet {

    private String id;

    private BigDecimal balance;


    private List<Transactions> transactions;

    public Wallet() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }
}
