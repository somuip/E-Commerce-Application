package com.example.ECommerceApplication.service.Impl;

import com.example.ECommerceApplication.dto.RequestDto.CheckoutCartRequestDto;
import com.example.ECommerceApplication.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.CartResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.ItemResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceApplication.exception.InvalidCardNumberException;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.model.*;
import com.example.ECommerceApplication.repository.*;
import com.example.ECommerceApplication.service.CartService;
import com.example.ECommerceApplication.service.OrderService;
import com.example.ECommerceApplication.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;
    @Autowired
    ItemRepository itemRepository;


    @Override
    public CartResponseDto saveCart(int customerId, Item item) {

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getTotalCost() + item.getRequiredQuantity() * item.getProduct().getPrice();
        cart.setTotalCost(newTotal);
        cart.getTotalItems().add(item);
        cart.setNumberOfItems(cart.getTotalItems().size());
        item.setCart(cart);
        Cart savedCart = cartRepository.save(cart);

        // prepare for response
        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(savedCart.getTotalCost())
                .customerName(customer.getName())
                .numberOfItems(savedCart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemsList = new ArrayList<>();
        for (Item itemEntity : savedCart.getTotalItems()) {
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponse(itemEntity);
            itemsList.add(itemResponseDto);
        }

        // finally setting item response list to cart response list of items
        cartResponseDto.setItems(itemsList);
        return cartResponseDto;
    }

    @Override
    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {
        // check for customer
        Customer customer;
        try {
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new InvalidCustomerException("Sorry!!, customer id is invalid");
        }

        // check if card cvv is matching  && customer, cvv
        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if (checkoutCartRequestDto.getCvv() != card.getCVV() || card == null || customer != card.getCustomer()) {
            throw new InvalidCardNumberException("Sorry!!,Card number not valid");
        }

        // check cart
        Cart cart = customer.getCart();
        if (cart.getNumberOfItems() == 0) {
            throw new Exception("Cart is empty!!");
        }

        try {
            Orders orders = orderService.placeOrder(customer, card);
            customer.getOrders().add(orders);
            resetCart(cart);
            Orders savedOrders = orderRepository.save(orders);

            // prepare for response
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrders.getOrderDate());
            orderResponseDto.setOrderNo(savedOrders.getOrderNo());
            orderResponseDto.setCardUsed(savedOrders.getCardUsed());
            orderResponseDto.setTotalValue(savedOrders.getTotalCost());
            orderResponseDto.setCustomerName(customer.getName());

            List<ItemResponseDto> list = new ArrayList<>();
            for (Item item : savedOrders.getItems()) {
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponse(item);
                list.add(itemResponseDto);
            }
            orderResponseDto.setItems(list);
            return orderResponseDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//    @Override
//    public String deleteItemFromCart(ItemRequestDto itemRequestDto) throws InvalidCustomerException {
//        Customer customer;
//        try{
//            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
//        }catch (Exception e){
//            throw new InvalidCustomerException("Customer is invalid");
//        }
//
//        Cart cart = customer.getCart();
//
//        cart.g
//    }

    @Override
    public List<ItemResponseDto> getAllItemsInCart(String customerMobNo) throws InvalidCustomerException {
        Customer customer;
        try{
            customer = customerRepository.findByMobNo(customerMobNo);
        }catch (Exception e){
            throw new InvalidCustomerException("Sorry!! invalid customer");
        }
        Cart cart = customer.getCart();
        List<Item> itemList = cart.getTotalItems();

        // prepare for response
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : itemList){
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponse(item);
            itemResponseDtoList.add(itemResponseDto);
        }
        return itemResponseDtoList;
    }

    public void resetCart(Cart cart) {

        cart.setTotalCost(0);
        for (Item item : cart.getTotalItems()) {
            item.setCart(null);
        }
        cart.setNumberOfItems(0);
        cart.getTotalItems().clear();

    }
}
