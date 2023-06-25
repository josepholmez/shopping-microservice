package com.olmez.orderservice.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olmez.orderservice.model.OrderRequestModel;
import com.olmez.orderservice.repository.OrderRepository;
import com.olmez.orderservice.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderRepository productRepository;
    @Mock
    private OrderService productService;
    @Mock
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void createOrder() throws Exception {
        OrderRequestModel model = getOrderRequestModel();
        String modelAsString = objectMapper.writeValueAsString(model);

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(modelAsString));

        assertThat(actions.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    private OrderRequestModel getOrderRequestModel() {
        return OrderRequestModel.builder()
                .name("Samsung 20S")
                .description("Galaxy 20S Red color")
                .price(1000L)
                .build();
    }

}
