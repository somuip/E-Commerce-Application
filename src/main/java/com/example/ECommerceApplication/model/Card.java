package com.example.ECommerceApplication.model;

import com.example.ECommerceApplication.enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;

import java.util.Date;

@Entity
@Table(name="card")
@Data // alternate for getter and setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) // makes all attributes private
@Builder // another way of creating objects
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int CVV;

    @Column(unique = true,nullable = false)
    String cardNo;

    Date expiry;
    @Enumerated(EnumType.STRING)
    CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;


}
