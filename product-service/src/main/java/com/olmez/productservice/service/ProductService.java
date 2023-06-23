package com.olmez.productservice.service;

import java.util.List;

import com.olmez.productservice.model.ProductRequestModel;
import com.olmez.productservice.model.ProductResponseModel;

public interface ProductService {

    void createProduct(ProductRequestModel requestModel);

    List<ProductResponseModel> getAllProducts();

}
