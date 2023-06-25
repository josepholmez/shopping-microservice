package com.olmez.productservice.service;

import java.util.List;

import com.olmez.productservice.dto.ProductRequestDto;
import com.olmez.productservice.dto.ProductResponseDto;

public interface ProductService {

    void createProduct(ProductRequestDto requestDto);

    List<ProductResponseDto> getAllProducts();

}
