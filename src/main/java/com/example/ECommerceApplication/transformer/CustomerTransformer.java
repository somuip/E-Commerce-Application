package com.example.ECommerceApplication.transformer;

import com.example.ECommerceApplication.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto2;
import com.example.ECommerceApplication.model.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .address(customerRequestDto.getAddress())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .age(customerRequestDto.getAge())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponse(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome "+ customer.getName() +" to Amazon")
                .build();
    }

    public static CustomerResponseDto2 CustomerToCustomerResponse2(Customer customer){
        return CustomerResponseDto2.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .address(customer.getAddress())
                .build();
    }
}
