package com.practice.mypay.paymentsservice.model;

import java.math.BigDecimal;
import java.util.List;

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
