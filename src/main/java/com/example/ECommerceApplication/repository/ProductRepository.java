package com.example.ECommerceApplication.repository;

import com.example.ECommerceApplication.enums.Category;
import com.example.ECommerceApplication.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(Category category);

    // combining 2 variables

    @Query(value = "select p from Product p where p.price > :price and p.category=:category")
    List<Product> getProductsByPriceAndCategory(int price, Category category);
}
