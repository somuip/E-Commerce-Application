package com.example.ECommerceApplication.transformer;

import com.example.ECommerceApplication.dto.RequestDto.ProductRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.ProductResponseDto;
import com.example.ECommerceApplication.enums.Category;
import com.example.ECommerceApplication.enums.ProductStatus;
import com.example.ECommerceApplication.model.Product;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .price(productRequestDto.getPrice())
                .category(productRequestDto.getCategory())
                .name(productRequestDto.getName())
                .quantity(productRequestDto.getQuantity())
                .build();
    }

    public static ProductResponseDto ProductToProductResponse(Product product){
        return ProductResponseDto.builder()
                .name(product.getName())
                .sellerName(product.getSeller().getName())
                .productStatus(ProductStatus.AVAILABLE)
                .quantity(product.getQuantity())
                .build();
    }
}
