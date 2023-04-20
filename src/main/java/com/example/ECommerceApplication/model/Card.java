package com.example.ECommerceApplication.model;

import com.example.ECommerceApplication.enums.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;

@Entity
@Table(name="card")
@Data // alternate for getter and setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // makes all attributes private
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String CVV;
    String cardNo;
    String expiry;
    CardType cardType;

    @OneToOne
    Customer customer;

    @OneToOne
    Product product;

//    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
//    Orders orders;
}
