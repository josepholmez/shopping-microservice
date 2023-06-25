package com.olmez.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemDto {
    private Long id;
    private String skuCode;
    private Long price;
    private Integer quantity;
}
