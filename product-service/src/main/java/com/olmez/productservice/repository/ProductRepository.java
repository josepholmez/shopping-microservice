package com.olmez.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olmez.productservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
