package com.example.ECommerceApplication.service;

import com.example.ECommerceApplication.dto.RequestDto.SellerRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.SellerResponseDto;
import com.example.ECommerceApplication.exception.EmailAlreadyExistException;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyExistException;
}
