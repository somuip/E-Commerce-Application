package com.example.ECommerceApplication.service.Impl;

import com.example.ECommerceApplication.dto.RequestDto.ProductRequestDto;
import com.example.ECommerceApplication.dto.ResponseDto.ProductResponseDto;
import com.example.ECommerceApplication.enums.Category;
import com.example.ECommerceApplication.enums.ProductStatus;
import com.example.ECommerceApplication.exception.InvalidSellerException;
import com.example.ECommerceApplication.exception.ProductOutStockException;
import com.example.ECommerceApplication.model.Item;
import com.example.ECommerceApplication.model.Product;
import com.example.ECommerceApplication.model.Seller;
import com.example.ECommerceApplication.repository.ProductRepository;
import com.example.ECommerceApplication.repository.SellerRepository;
import com.example.ECommerceApplication.service.ProductService;
import com.example.ECommerceApplication.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getId()).get();
        }
        catch (Exception e){
            throw new InvalidSellerException("Seller is not found");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        product.setProductStatus(ProductStatus.AVAILABLE);

        // add product to current products of seller
        seller.getProducts().add(product);
        sellerRepository.save(seller);  // this will save both product and seller

        // prepare for response
        return ProductTransformer.ProductToProductResponse(product);
    }

    public List<ProductResponseDto> getAllProductsByCategory(Category category){

        List<Product> products = productRepository.findByCategory(category);

        List<ProductResponseDto> productResponseDto = new ArrayList<>();
        for(Product product: products){
            productResponseDto.add(ProductTransformer.ProductToProductResponse(product));
        }

        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, Category category) {

        List<Product> products = productRepository.getProductsByPriceAndCategory(price, category);

        // prepare for response
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponse(product));
        }
        return productResponseDtos;
    }

    @Override
    public void decreaseProductQuantity(Item item) throws ProductOutStockException {
        Product product = item.getProduct();
        int quantity = item.getRequiredQuantity();
        int currentQuantity = product.getQuantity();
        if(quantity > currentQuantity){
            throw new ProductOutStockException("Sorry!!, product is out of stock");
        }
        product.setQuantity(currentQuantity - quantity);
        if(product.getQuantity() == 0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
    }
}
