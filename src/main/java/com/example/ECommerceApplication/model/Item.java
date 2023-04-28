package com.example.ECommerceApplication.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder // another way of creating objects
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    int requiredQuantity;
    int cost;

    @ManyToOne
    Cart cart;

    @OneToOne
    Product product;

    @ManyToOne
    Orders orders;



}
