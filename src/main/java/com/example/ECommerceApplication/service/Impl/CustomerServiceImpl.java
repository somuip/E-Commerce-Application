package com.example.ECommerceApplication.service.Impl;

import com.example.ECommerceApplication.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto2;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.exception.MobileNoAlreadyExistsException;
import com.example.ECommerceApplication.model.Card;
import com.example.ECommerceApplication.model.Cart;
import com.example.ECommerceApplication.model.Customer;
import com.example.ECommerceApplication.repository.CartRepository;
import com.example.ECommerceApplication.repository.CustomerRepository;
import com.example.ECommerceApplication.service.CustomerService;
import com.example.ECommerceApplication.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

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

    @Override
    public List<CustomerResponseDto2> viewAllCustomers() {

        List<Customer> customerList = new ArrayList<>();

        for(Customer customer:customerRepository.findAll()){
            customerList.add(customer);
        }

        // prepare for response
        List<CustomerResponseDto2> customerResponseDtoList = new ArrayList<>();
        for(Customer customer:customerList){
            CustomerResponseDto2 customerResponseDto2 = CustomerTransformer.CustomerToCustomerResponse2(customer);
            customerResponseDtoList.add(customerResponseDto2);
        }

        return customerResponseDtoList;
    }

    @Override
    public CustomerResponseDto2 getCustomerByEmail(String email) throws InvalidCustomerException {
        Customer customer;
        try{
            customer = customerRepository.findByEmail(email);
        }catch (Exception e){
            throw new InvalidCustomerException("Sorry!! customer not found, try with other email");
        }

        // prepare for response
        return CustomerTransformer.CustomerToCustomerResponse2(customer);
    }

    @Override
    public String deleteCustomerByMobNo(String mobNo) throws InvalidCustomerException {
        Customer customer;
        try{
            customer = customerRepository.findByMobNo(mobNo);
        }catch (Exception e){
            throw new InvalidCustomerException("Sorry!! customer not found, try with other mobNo");
        }

        Cart cart = customer.getCart();
        cartRepository.delete(cart);

        customerRepository.delete(customer);
        return "Customer deleted successfully";
    }
}
