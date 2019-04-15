package com.practice.mypay.dbservice.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/*
1. CascadeType=Cascade.ALL to overcome below scenario

    exception: object references an unsaved transient instance - save the transient instance before flushing
    will occur when you try to persist / merge an entity with a reference to another entity which happens to be detached.

    When creating or removing an object which also includes creation or deletion of other objects(dependencies), this
    should be used.


2.  @GeneratedValue(generator ="system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
        can be used instead of @GeneratedValue(STRATEGY=GeneratorType.AUTO) when trying to generate a value of type String.

3. @OneToMany
        used while mapping a single object to collection of objects.
        we can also store a list as an object if we use @ElementCollection

 */

@Entity
@Table(name = "Customer_DB")
public class Customer {

    @Id
    private String phoneNumber;
    @Column
    private String name;

    @OneToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Wallet wallet;

    public Customer() {
    }

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
