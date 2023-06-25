package com.olmez.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olmez.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
