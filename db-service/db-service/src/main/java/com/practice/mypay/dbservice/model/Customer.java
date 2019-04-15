package com.practice.mypay.dbservice.model;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Entity
@Table(name = "Customer_DB")
public class Customer {

    @Id
    @Column
    private String phoneNumber;
    @Column
    private String name;

    @Embedded
    private Wallet wallet;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
