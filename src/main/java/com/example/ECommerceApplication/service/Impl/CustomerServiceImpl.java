package com.example.ECommerceApplication.service.Impl;

import com.example.ECommerceApplication.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerceApplication.exception.MobileNoAlreadyExistsException;
import com.example.ECommerceApplication.model.Cart;
import com.example.ECommerceApplication.model.Customer;
import com.example.ECommerceApplication.repository.CustomerRepository;
import com.example.ECommerceApplication.service.CustomerService;
import com.example.ECommerceApplication.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyExistsException {

        if(customerRepository.findByMobNo(customerRequestDto.getMobNo()) != null){
            throw new MobileNoAlreadyExistsException("Sorry! mobile number already used!");
        }

        // request dto--> customer
        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerRequestDto);
        // set cart
        Cart cart = Cart.builder()
                .customer(customer)
                .totalCost(0)
                .numberOfItems(0)
                .build();

        customer.setCart(cart);
        customerRepository.save(customer);

        // prepare for response

       return CustomerTransformer.CustomerToCustomerResponse(customer);
    }
}
