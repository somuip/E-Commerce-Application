package com.example.ECommerceApplication.dto.RequestDto;

import com.example.ECommerceApplication.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder // another way of creating objects
public class CardRequestDto {

    String mobNo;

    int cvv;

    String cardNo;

    Date expiry;

    CardType cardType;
}
