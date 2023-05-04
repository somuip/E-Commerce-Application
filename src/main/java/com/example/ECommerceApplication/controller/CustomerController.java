package com.example.ECommerceApplication.controller;

import com.example.ECommerceApplication.dto.RequestDto.CustomerRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.CustomerResponseDto2;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.exception.MobileNoAlreadyExistsException;
import com.example.ECommerceApplication.model.Customer;
import com.example.ECommerceApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("add-customer")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileNoAlreadyExistsException {
        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/get-all-customers")
    public List<CustomerResponseDto2> viewAllCustomers(){
        return customerService.viewAllCustomers();
    }

    @GetMapping("/get-customer-by-email")
    public CustomerResponseDto2 getCustomerByEmail(@RequestParam("email") String email) throws InvalidCustomerException {
        return customerService.getCustomerByEmail(email);
    }

    @DeleteMapping("delete-customer-by-mobile")
    public String deleteCustomerByMobNo(@RequestParam("mobNo") String mobNo) throws InvalidCustomerException {
        return customerService.deleteCustomerByMobNo(mobNo);
    }
}
