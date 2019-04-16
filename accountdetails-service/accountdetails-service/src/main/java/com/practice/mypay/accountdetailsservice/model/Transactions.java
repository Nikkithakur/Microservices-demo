package com.practice.mypay.accountdetailsservice.model;

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

public class Transactions {

    private String transactionId;

    private String transactionMsg;

    public Transactions() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionMsg() {
        return transactionMsg;
    }

    public void setTransactionMsg(String transactionMsg) {
        this.transactionMsg = transactionMsg;
    }
}
