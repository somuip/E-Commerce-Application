package com.example.ECommerceApplication.dto.RequestDto;

import com.example.ECommerceApplication.enums.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder // helps in creating objects
public class ProductRequestDto {

    int id;

    String name;

    int quantity;

    int price;

    Category category;
}
