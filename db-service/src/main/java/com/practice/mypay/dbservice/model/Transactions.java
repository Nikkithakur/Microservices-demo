package com.practice.mypay.dbservice.model;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter @Setter @NoArgsConstructor
public class Transactions {


    @GeneratedValue(generator ="system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Id
    private String transactionId;

    @Column
    private String transactionMsg;
}
