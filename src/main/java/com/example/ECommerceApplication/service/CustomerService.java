package com.example.ECommerceApplication.service;

import com.example.ECommerceApplication.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto2;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.exception.MobileNoAlreadyExistsException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyExistsException;



    public List<CustomerResponseDto2> viewAllCustomers();

    CustomerResponseDto2 getCustomerByEmail(String email) throws InvalidCustomerException;

    String deleteCustomerByMobNo(String mobNo) throws InvalidCustomerException;

}
