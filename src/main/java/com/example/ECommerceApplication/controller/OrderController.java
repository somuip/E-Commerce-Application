package com.example.ECommerceApplication.controller;

import com.example.ECommerceApplication.dto.RequestDto.OrderRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceApplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public OrderResponseDto directPlaceOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception {
        return orderService.placeOrder(orderRequestDto);
    }
}
