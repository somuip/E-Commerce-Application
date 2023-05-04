package com.example.ECommerceApplication.service;

import com.example.ECommerceApplication.dto.RequestDto.CardRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceApplication.enums.CardType;
import com.example.ECommerceApplication.exception.InvalidCustomerException;

import java.util.Date;
import java.util.List;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;

    List<CardResponseDto> getCardOfType(CardType cardType);

    List<CardResponseDto> getAllMasterCards(CardType cardType, Date expiryDate);
}
