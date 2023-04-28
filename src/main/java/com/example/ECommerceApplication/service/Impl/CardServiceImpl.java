package com.example.ECommerceApplication.service.Impl;

import com.example.ECommerceApplication.dto.RequestDto.CardRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CardResponseDto;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.model.Card;
import com.example.ECommerceApplication.model.Customer;
import com.example.ECommerceApplication.repository.CardRepository;
import com.example.ECommerceApplication.repository.CustomerRepository;
import com.example.ECommerceApplication.service.CardService;
import com.example.ECommerceApplication.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {
        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());
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
}

