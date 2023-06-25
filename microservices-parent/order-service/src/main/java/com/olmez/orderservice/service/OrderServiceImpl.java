package com.olmez.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olmez.orderservice.dto.OrderLineItemDto;
import com.olmez.orderservice.dto.OrderRequestDto;
import com.olmez.orderservice.model.Order;
import com.olmez.orderservice.model.OrderLineItem;
import com.olmez.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequestDto.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.getOrderLineItems().addAll(orderLineItems);
        order = orderRepository.save(order);
        log.info("Order {} is saved:", order.getId());
    }

    private OrderLineItem mapToDto(OrderLineItemDto oliDto) {
        OrderLineItem oli = new OrderLineItem();
        oli.setSkuCode(oliDto.getSkuCode());
        oli.setPrice(oliDto.getPrice());
        oli.setQuantity(oliDto.getQuantity());
        return oli;
    }

}
