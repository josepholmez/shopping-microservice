package com.olmez.orderservice.service;

import java.util.List;

import com.olmez.orderservice.model.OrderRequestModel;
import com.olmez.orderservice.model.OrderResponseModel;

public interface OrderService {

    void createOrder(OrderRequestModel requestModel);

    List<OrderResponseModel> getAllOrders();

}
