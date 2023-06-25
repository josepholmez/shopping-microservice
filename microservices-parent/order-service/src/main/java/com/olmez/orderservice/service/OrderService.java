package com.olmez.orderservice.service;

import com.olmez.orderservice.dto.OrderRequestDto;

public interface OrderService {

    void placeOrder(OrderRequestDto requestModel);

}
