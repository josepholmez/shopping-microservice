package com.olmez.orderservice.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequestDto {
    private final List<OrderLineItemDto> orderLineItemDtoList = new ArrayList<>();
}
