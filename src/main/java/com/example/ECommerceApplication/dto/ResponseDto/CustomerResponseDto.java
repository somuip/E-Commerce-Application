package com.example.ECommerceApplication.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder // another way of creating objects
public class CustomerResponseDto {
    int id;
    String name;
    String email;
    int age;
    String mobNo;
}
