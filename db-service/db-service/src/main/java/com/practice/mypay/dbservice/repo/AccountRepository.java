package com.practice.mypay.dbservice.repo;

import com.practice.mypay.dbservice.model.Customer;
import oracle.sql.TIMESTAMP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Customer,Integer> {

    Customer save(Customer customer);
    Customer findCustomerByPhoneNumber(String phoneNumber);
}
