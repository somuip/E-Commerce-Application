package com.example.ECommerceApplication.service.Impl;

import com.example.ECommerceApplication.dto.RequestDto.CardRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceApplication.enums.CardType;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.model.Card;
import com.example.ECommerceApplication.model.Customer;
import com.example.ECommerceApplication.repository.CardRepository;
import com.example.ECommerceApplication.repository.CustomerRepository;
import com.example.ECommerceApplication.service.CardService;
import com.example.ECommerceApplication.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {
        Customer customer = customerRepository.findByEmail(cardRequestDto.getEmail());
        if(customer == null){
            throw new InvalidCustomerException("Sorry! customer doesn't exist");
        }

        Card card = CardTransformer.CardRequestToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        customerRepository.save(customer); // this will save both card and customer because customer is parent

        // prepare response

        return CardTransformer.CardToCardResponse(card);
    }

    @Override
    public List<CardResponseDto> getCardOfType(CardType cardType) {
        List<Card> listCards = new ArrayList<>();
        for(int i=0;i<cardRepository.count();i++){
            Card card = cardRepository.findById(i).get();
            if(card.getCardType().equals(cardType)){
                listCards.add(card);
            }
        }

        // prepare for response

        List<CardResponseDto> cardResponseDtoList =  new ArrayList<>();

        for(Card card : listCards){
            CardResponseDto cardResponseDto = CardTransformer.CardToCardResponse(card);
            cardResponseDtoList.add(cardResponseDto);
        }

        return cardResponseDtoList;
    }

    @Override
    public List<CardResponseDto> getAllMasterCards(CardType cardType, Date expiryDate) {
        List<Card> cardList = new ArrayList<>();

        for(int i=0;i<cardRepository.count();i++) {
            Card card = cardRepository.findById(i).get();
            if(card.getCardType().equals(cardType) && card.getExpiry().equals(expiryDate))
                cardList.add(card);
        }

        // prepare for response

        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for(Card card : cardList){
            CardResponseDto cardResponseDto = CardTransformer.CardToCardResponse(card);
            cardResponseDtoList.add(cardResponseDto);
        }

        return cardResponseDtoList;
    }
}

