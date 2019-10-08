package com.practice.mypay.dbservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
@Table(name = "Customer")
@Getter @Setter @Builder
public class Customer {

    @Id
    private String phoneNumber;
    @Column
    private String name;

    @OneToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Wallet wallet;
}
