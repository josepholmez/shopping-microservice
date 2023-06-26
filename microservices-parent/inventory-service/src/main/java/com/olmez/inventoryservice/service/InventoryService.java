package com.olmez.inventoryservice.service;

import java.util.List;

import com.olmez.inventoryservice.dto.InventoryResponseDto;

public interface InventoryService {

    List<InventoryResponseDto> isInStock(List<String> skuCodes);

}
