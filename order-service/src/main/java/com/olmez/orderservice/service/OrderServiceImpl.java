package com.olmez.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.olmez.orderservice.model.Order;
import com.olmez.orderservice.model.OrderRequestModel;
import com.olmez.orderservice.model.OrderResponseModel;
import com.olmez.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void createOrder(OrderRequestModel requestModel) {
        Order order = new Order(requestModel.getName(), requestModel.getDescription(), requestModel.getPrice());
        order = orderRepository.save(order);
        log.info("Product {} is saved:", order.getId());
    }

    @Override
    public List<OrderResponseModel> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    private OrderResponseModel mapToProductResponse(Order order) {
        return OrderResponseModel.builder()
                .id(order.getId())
                .name(order.getName())
                .description(order.getDescription())
                .price(order.getPrice())
                .build();
    }

}
