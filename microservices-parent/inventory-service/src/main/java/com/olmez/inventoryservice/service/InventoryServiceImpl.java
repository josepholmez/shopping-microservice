package com.olmez.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olmez.inventoryservice.dto.InventoryResponseDto;
import com.olmez.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDto> isInStock(List<String> skuCodes) {
        return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
                .map(irdto -> InventoryResponseDto.builder()
                        .skuCode(irdto.getSkuCode())
                        .isInStock(irdto.getQuantity() > 0)
                        .build())
                .toList();
    }

}
