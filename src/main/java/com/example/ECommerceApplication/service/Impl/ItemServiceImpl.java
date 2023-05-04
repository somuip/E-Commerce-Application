package com.example.ECommerceApplication.service.Impl;

import com.example.ECommerceApplication.dto.RequestDto.ItemRequestDto;
import com.example.ECommerceApplication.enums.ProductStatus;
import com.example.ECommerceApplication.exception.InvalidCustomerException;
import com.example.ECommerceApplication.exception.InvalidProductException;
import com.example.ECommerceApplication.exception.ProductOutStockException;
import com.example.ECommerceApplication.model.Customer;
import com.example.ECommerceApplication.model.Item;
import com.example.ECommerceApplication.model.Product;
import com.example.ECommerceApplication.repository.CustomerRepository;
import com.example.ECommerceApplication.repository.ItemRepository;
import com.example.ECommerceApplication.repository.ProductRepository;
import com.example.ECommerceApplication.service.ItemService;
import com.example.ECommerceApplication.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidCustomerException, InvalidProductException, ProductOutStockException {
        // check for customer
        Customer customer;
        try {
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new InvalidCustomerException("Sorry!!, customer not found!!");
        }

        // check for product
        Product product;
        try {
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        } catch (Exception e) {
            throw new InvalidProductException("Sorry!!, Product not found");
        }

        // check if required quantity and product status
        if (itemRequestDto.getRequiredQuantity() > product.getQuantity() || product.getProductStatus() != ProductStatus.AVAILABLE) {
            throw new ProductOutStockException("Product out stock");
        }

        // item request to item
        Item item = ItemTransformer.ItemRequestToItem(itemRequestDto);
        item.setProduct(product);

        product.getItems().add(item);

        return itemRepository.save(item);
    }
}
