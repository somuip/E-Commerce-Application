package com.example.ECommerceApplication.controller;

import com.example.ECommerceApplication.dto.RequestDto.ProductRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.ProductResponseDto;
import com.example.ECommerceApplication.enums.Category;
import com.example.ECommerceApplication.exception.InvalidSellerException;
import com.example.ECommerceApplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {
        return productService.addProduct(productRequestDto);
    }

    // get all products of a particular category
    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("category") Category category){
        return productService.getAllProductsByCategory(category);
    }

    // get all products having price greater than given price and category
    @GetMapping("/get/{price}/{category}")
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(
            @PathVariable("price") int price,
            @PathVariable("category") Category category){

        return productService.getAllProductsByPriceAndCategory(price, category);
    }
}
