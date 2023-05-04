package com.example.ECommerceApplication.controller;


import com.example.ECommerceApplication.dto.RequestDto.CheckoutCartRequestDto;
import com.example.ECommerceApplication.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.ItemResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.model.Customer;
import com.example.ECommerceApplication.model.Item;
import com.example.ECommerceApplication.service.CartService;
import com.example.ECommerceApplication.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception {

        try{
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(),savedItem);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/check-out")
    public OrderResponseDto checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        return cartService.checkoutCart(checkoutCartRequestDto);

    }

//    @DeleteMapping("/delete-item")
//    public String deleteItemFromCart(@RequestBody ItemRequestDto itemRequestDto){
//        return cartService.deleteItemFromCart(itemRequestDto);
//    }

    @GetMapping("/view-all-items")
    public List<ItemResponseDto>  getAllItemsInCart(@RequestParam String customerMobNo) throws InvalidCustomerException {
        return cartService.getAllItemsInCart(customerMobNo);
    }


}