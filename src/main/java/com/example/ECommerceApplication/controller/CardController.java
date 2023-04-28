package com.example.ECommerceApplication.controller;

import com.example.ECommerceApplication.dto.RequestDto.CardRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add-card")
    public ResponseEntity addCard(CardRequestDto cardRequestDto){
        try{
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.ACCEPTED);
        }
        catch (InvalidCustomerException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
