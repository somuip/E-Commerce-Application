package com.example.ECommerceApplication.service;

import com.example.ECommerceApplication.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto;
import org.springframework.stereotype.Service;


public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

}
