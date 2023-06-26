package com.olmez.inventoryservice.inventorytests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olmez.inventoryservice.controller.InventoryController;
import com.olmez.inventoryservice.repository.InventoryRepository;
import com.olmez.inventoryservice.service.InventoryService;

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {

    @InjectMocks
    private InventoryController productController;

    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private InventoryService inventoryService;
    @Mock
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void isInStock() {
    }

}
