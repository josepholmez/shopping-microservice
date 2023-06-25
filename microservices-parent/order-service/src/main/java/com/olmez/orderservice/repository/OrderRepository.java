package com.olmez.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olmez.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
