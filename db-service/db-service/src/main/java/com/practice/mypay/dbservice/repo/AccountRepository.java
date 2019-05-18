package com.practice.mypay.dbservice.repo;

import com.practice.mypay.dbservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Customer,Integer> {

    Customer save(Customer customer);
    Customer findCustomerByPhoneNumber(String phoneNumber);
}
