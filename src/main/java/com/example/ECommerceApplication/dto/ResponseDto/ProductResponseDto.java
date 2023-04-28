package com.example.ECommerceApplication.dto.ResponseDto;

import com.example.ECommerceApplication.enums.Category;
import com.example.ECommerceApplication.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder // helps in creating objects
public class ProductResponseDto {

    String name;
    String sellerName;
    ProductStatus productStatus;
    int quantity;
}
