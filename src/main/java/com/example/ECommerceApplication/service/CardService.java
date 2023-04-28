package com.example.ECommerceApplication.service;

import com.example.ECommerceApplication.dto.RequestDto.CardRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceApplication.exception.InvalidCustomerException;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
}
