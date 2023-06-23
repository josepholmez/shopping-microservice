package com.olmez.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.olmez.productservice.model.Product;
import com.olmez.productservice.model.ProductRequestModel;
import com.olmez.productservice.model.ProductResponseModel;
import com.olmez.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequestModel requestModel) {
        Product product = Product.builder()
                .name(requestModel.getName())
                .description(requestModel.getDescription())
                .price(requestModel.getPrice())
                .build();
        product = productRepository.save(product);
        log.info("Product {} is saved:", product.getId());
    }

    @Override
    public List<ProductResponseModel> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    private ProductResponseModel mapToProductResponse(Product product) {
        return ProductResponseModel.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}
