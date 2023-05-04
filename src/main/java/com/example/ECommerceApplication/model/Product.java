package com.example.ECommerceApplication.model;

import com.example.ECommerceApplication.enums.Category;
import com.example.ECommerceApplication.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder // another way of creating objects
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int quantity;

    int price;

    @Enumerated(EnumType.STRING)
    Category category;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @ManyToOne
    Seller seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

}
