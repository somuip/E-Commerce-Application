package com.example.ECommerceApplication.controller;

import com.example.ECommerceApplication.dto.RequestDto.CardRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceApplication.enums.CardType;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add-card")
    public CardResponseDto addCard(@RequestBody CardRequestDto cardRequestDto) throws InvalidCustomerException {
        return cardService.addCard(cardRequestDto);
    }

    @GetMapping("/get-card")
    public List<CardResponseDto> getCard(@PathVariable CardType cardType){
        return cardService.getCardOfType(cardType);
    }

    @GetMapping("/get-all-mastercard-expiry-greater-than-date")
    public List<CardResponseDto> getMasterCards(@RequestParam("cardType") CardType cardType, @RequestParam("date")Date expiryDate){
        return cardService.getAllMasterCards(cardType, expiryDate);
    }
}
