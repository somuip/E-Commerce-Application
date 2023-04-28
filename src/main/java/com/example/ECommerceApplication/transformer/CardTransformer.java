package com.example.ECommerceApplication.transformer;

import com.example.ECommerceApplication.dto.RequestDto.CardRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceApplication.model.Card;

public class CardTransformer {

    public static Card CardRequestToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .CVV(cardRequestDto.getCvv())
                .expiry(cardRequestDto.getExpiry())
                .build();
    }

    public static CardResponseDto CardToCardResponse(Card card){
        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .customerName(card.getCustomer().getName())
                .build();
    }
}
