package com.olmez.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.olmez.productservice.dto.ProductRequestDto;
import com.olmez.productservice.dto.ProductResponseDto;
import com.olmez.productservice.model.Product;
import com.olmez.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(productRequestDto.getName(), productRequestDto.getDescription(),
                productRequestDto.getPrice());
        product = productRepository.save(product);
        log.info("Product {} is saved:", product.getId());
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    private ProductResponseDto mapToProductResponse(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}
