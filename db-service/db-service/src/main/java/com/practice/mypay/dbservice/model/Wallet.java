package com.practice.mypay.dbservice.model;


import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.List;

@Embeddable
public class Wallet {

    @Column
    private BigDecimal balance;

    @Column
    @ElementCollection
    private List<String> transactions;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }
}
