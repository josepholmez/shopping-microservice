package com.olmez.orderservice.ordertests;

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
import com.olmez.orderservice.controller.OrderController;
import com.olmez.orderservice.dto.OrderLineItemDto;
import com.olmez.orderservice.dto.OrderRequestDto;
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
    void placeOrder() throws Exception {
        OrderLineItemDto oliDto = new OrderLineItemDto(2L, "sku1234", 550L, 4);
        OrderRequestDto orDto = new OrderRequestDto();
        orDto.getOrderLineItemDtoList().add(oliDto);

        String modelAsString = objectMapper.writeValueAsString(orDto);

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(modelAsString));

        assertThat(actions.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

}
