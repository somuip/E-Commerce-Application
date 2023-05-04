package com.example.ECommerceApplication.service;

import com.example.ECommerceApplication.dto.RequestDto.OrderRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.exception.InvalidProductException;
import com.example.ECommerceApplication.exception.ProductOutStockException;
import com.example.ECommerceApplication.model.Card;
import com.example.ECommerceApplication.model.Customer;
import com.example.ECommerceApplication.model.Orders;

public interface OrderService {

    public Orders placeOrder(Customer customer, Card card) throws ProductOutStockException;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;
}
