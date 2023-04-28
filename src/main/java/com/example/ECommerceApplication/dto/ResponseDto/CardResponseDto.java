package com.example.ECommerceApplication.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder // another way of creating objects
public class CardResponseDto {

    String cardNo;
    String customerName;

}
