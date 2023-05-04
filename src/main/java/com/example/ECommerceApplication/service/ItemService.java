package com.example.ECommerceApplication.service;

import com.example.ECommerceApplication.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.ItemResponseDto;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.exception.InvalidProductException;
import com.example.ECommerceApplication.exception.ProductOutStockException;
import com.example.ECommerceApplication.model.Item;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

public interface ItemService {

    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidCustomerException, InvalidProductException, ProductOutStockException;
 }
