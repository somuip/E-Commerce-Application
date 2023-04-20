package com.example.ECommerceApplication.service.Impl;

import com.example.ECommerceApplication.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerceApplication.model.Customer;
import com.example.ECommerceApplication.repository.CustomerRepository;
import com.example.ECommerceApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();

        customer.setName(customerRequestDto.getName());
        customer.setAge(customerRequestDto.getAge());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setMobNo(customerRequestDto.getMobNo());

        customerRepository.save(customer);

        // prepare for response

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(customer.getId());
        customerResponseDto.setName(customerRequestDto.getName());
        customerResponseDto.setAge(customerRequestDto.getAge());
        customerResponseDto.setEmail(customerRequestDto.getEmail());
        customerResponseDto.setMobNo(customerRequestDto.getMobNo());

        return customerResponseDto;
    }
}
