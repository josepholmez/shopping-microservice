package com.olmez.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.olmez.orderservice.dto.InventoryResponseDto;
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
    private final WebClient.Builder webClientBuilder;

    @Override
    public void placeOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequestDto.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.getOrderLineItems().addAll(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream()
                .map(OrderLineItem::getSkuCode)
                .toList();
        // Call inventory service, and place order if product is in stock
        // this OrderController daki get methodununu temsil eder-verilen parametre ile
        // cagirir. We can use "inventory-service" instead of "localhost:5003" since we
        // define this in application.yml
        InventoryResponseDto[] inventoryResponseDtoList = webClientBuilder.build().get()
                .uri("http://localhost:5003/api/inventory",
                        urlBuilder -> urlBuilder.queryParam("skuCodes", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponseDto[].class)
                .block();

        boolean allProductInStock = Arrays.stream(inventoryResponseDtoList)
                .allMatch(InventoryResponseDto::isInStock);

        if (allProductInStock) {
            order = orderRepository.save(order);
            log.info("Order {} is saved:", order.getId());
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    private OrderLineItem mapToDto(OrderLineItemDto oliDto) {
        OrderLineItem oli = new OrderLineItem();
        oli.setSkuCode(oliDto.getSkuCode());
        oli.setPrice(oliDto.getPrice());
        oli.setQuantity(oliDto.getQuantity());
        return oli;
    }

}
