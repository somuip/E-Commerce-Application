package com.example.ECommerceApplication.model;

import com.example.ECommerceApplication.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder // another way of creating objects
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    Date orderDate;
    int totalCost;
    String orderNo;
    OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    List<Item> totalItems = new ArrayList<>();



}
