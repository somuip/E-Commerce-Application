package com.example.ECommerceApplication.service.Impl;


import com.example.ECommerceApplication.dto.RequestDto.OrderRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.ItemResponseDto;
import com.example.ECommerceApplication.dto.ResponseDto.OrderResponseDto;
import com.example.ECommerceApplication.enums.ProductStatus;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.exception.InvalidProductException;
import com.example.ECommerceApplication.exception.ProductOutStockException;
import com.example.ECommerceApplication.model.*;
import com.example.ECommerceApplication.repository.CardRepository;
import com.example.ECommerceApplication.repository.CustomerRepository;
import com.example.ECommerceApplication.repository.OrderRepository;
import com.example.ECommerceApplication.repository.ProductRepository;
import com.example.ECommerceApplication.service.OrderService;
import com.example.ECommerceApplication.service.ProductService;
import com.example.ECommerceApplication.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;


    @Override
    public Orders placeOrder(Customer customer, Card card) throws ProductOutStockException {

        Cart cart = customer.getCart();

        Orders orders = new Orders();
        orders.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNumber = generateMaskedCardNumber(card.getCardNo());
        orders.setCardUsed(maskedCardNumber);
        orders.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item: cart.getTotalItems()){
            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            }catch (Exception e){
                throw new ProductOutStockException("Sorry!!, product out of stock");
            }
        }
        orders.setItems(orderedItems);
        for(Item item: orderedItems){
            item.setOrders(orders);
        }
        orders.setTotalCost(cart.getTotalCost());
        return orders;
    }

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        // check for customer
        Customer customer;
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new InvalidCustomerException("Sorry!!, customer not found!!");
        }

        // check for product
        Product product;
        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        } catch (Exception e) {
            throw new InvalidProductException("Sorry!!, Product not found");
        }

        // check if required quantity and product status
        if (orderRequestDto.getRequiredQuantity() > product.getQuantity() || product.getProductStatus() != ProductStatus.AVAILABLE) {
            throw new ProductOutStockException("Product out stock");
        }

        Item item = Item.builder()
                .product(product)
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .build();

        try {
            productService.decreaseProductQuantity(item);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        Orders orders = new Orders();
        orders.setTotalCost(orderRequestDto.getRequiredQuantity() * product.getPrice());
        orders.setOrderNo(String.valueOf(UUID.randomUUID()));
        String masked = generateMaskedCardNumber(orderRequestDto.getCardNo());
        orders.setCardUsed(masked);
        orders.setCustomer(customer);
        orders.getItems().add(item);

        customer.getOrders().add(orders);
        item.setOrders(orders);
        product.getItems().add(item);

        Orders savedOrders = orderRepository.save(orders);

        // prepare for response
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate(savedOrders.getOrderDate());
        orderResponseDto.setOrderNo(savedOrders.getOrderNo());
        orderResponseDto.setCardUsed(savedOrders.getCardUsed());
        orderResponseDto.setTotalValue(savedOrders.getTotalCost());
        orderResponseDto.setCustomerName(customer.getName());

        List<ItemResponseDto> list = new ArrayList<>();
        for (Item items : savedOrders.getItems()) {
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponse(item);
            list.add(itemResponseDto);
        }
        orderResponseDto.setItems(list);
        return orderResponseDto;
    }


    public String generateMaskedCardNumber(String cardNo){
        String masked = "";
        for(int i=0;i<cardNo.length()-4;i++){
            masked += 'X';
        }
        masked += cardNo.substring(cardNo.length()-4);
        return masked;
    }
}
