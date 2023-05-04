package com.example.ECommerceApplication.service;

import com.example.ECommerceApplication.dto.RequestDto.CheckoutCartRequestDto;
import com.example.ECommerceApplication.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.ItemResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceApplication.exception.InvalidCardNumberException;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.model.Customer;
import com.example.ECommerceApplication.model.Item;

import java.util.List;

public interface CartService {

    public CartResponseDto saveCart(int customerId, Item item);

    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception;

//    String deleteItemFromCart(ItemRequestDto itemRequestDto) throws InvalidCustomerException;

    List<ItemResponseDto> getAllItemsInCart(String customerMobNo) throws InvalidCustomerException;
}
