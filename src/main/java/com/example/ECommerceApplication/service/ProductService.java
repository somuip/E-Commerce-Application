package com.example.ECommerceApplication.service;

import com.example.ECommerceApplication.dto.RequestDto.ProductRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.ProductResponseDto;
import com.example.ECommerceApplication.enums.Category;
import com.example.ECommerceApplication.exception.InvalidSellerException;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    public List<ProductResponseDto> getAllProductsByCategory(Category category);
}
