package com.example.ECommerceApplication.dto.RequestDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder // another way of creating objects
public class CustomerRequestDto {

    String name;
    String email;
    int age;
    String mobNo;
}


