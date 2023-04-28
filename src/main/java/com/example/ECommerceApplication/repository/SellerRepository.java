package com.example.ECommerceApplication.repository;

import com.example.ECommerceApplication.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Seller findByEmail(String email);
}
