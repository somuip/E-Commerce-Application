package com.example.ECommerceApplication.service.Impl;

import com.example.ECommerceApplication.dto.RequestDto.SellerRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.SellerResponseDto;
import com.example.ECommerceApplication.exception.EmailAlreadyExistException;
import com.example.ECommerceApplication.model.Seller;
import com.example.ECommerceApplication.repository.SellerRepository;
import com.example.ECommerceApplication.service.SellerService;
import com.example.ECommerceApplication.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyExistException {

        // check if email exist
        if(sellerRepository.findByEmail(sellerRequestDto.getEmailId()) != null){
            throw new EmailAlreadyExistException("Email already exist");
        }
        Seller seller = SellerTransformer.SellerRequestToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);

        // prepare for response
        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponse(savedSeller);
        return sellerResponseDto;
    }
}
