package com.example.ECommerceApplication.repository;

import com.example.ECommerceApplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByMobNo(String mobNo);
    Customer findByEmail(String email);
}
