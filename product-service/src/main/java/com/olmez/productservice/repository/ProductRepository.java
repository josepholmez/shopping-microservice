package com.olmez.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olmez.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
