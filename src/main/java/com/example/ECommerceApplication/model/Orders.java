package com.example.ECommerceApplication.model;

import com.example.ECommerceApplication.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String date;
    int totalCost;
    int orderNo;
    OrderStatus orderStatus;

    @ManyToOne
    Customer customer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    List<Item> totalItems = new ArrayList<>();

//    @OneToOne
//    Card card;


}
