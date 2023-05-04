package com.example.ECommerceApplication.transformer;

import com.example.ECommerceApplication.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.ItemResponseDto;
import com.example.ECommerceApplication.model.Item;

public class ItemTransformer {

    public static Item ItemRequestToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static ItemResponseDto ItemToItemResponse(Item item){
        return ItemResponseDto.builder()
                .productName(item.getProduct().getName())
                .priceOfOneItem(item.getProduct().getPrice())
                .quantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity() * item.getProduct().getPrice())
                .build();
    }
}
