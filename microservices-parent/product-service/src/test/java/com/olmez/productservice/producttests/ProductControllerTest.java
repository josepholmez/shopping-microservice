package com.olmez.productservice.producttests;

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
import com.olmez.productservice.controller.ProductController;
import com.olmez.productservice.dto.ProductRequestDto;
import com.olmez.productservice.repository.ProductRepository;
import com.olmez.productservice.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductService productService;
    @Mock
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void createProduct() throws Exception {
        ProductRequestDto model = getProductRequestDto();
        String modelAsString = objectMapper.writeValueAsString(model);

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(modelAsString));

        assertThat(actions.andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ProductRequestDto getProductRequestDto() {
        return ProductRequestDto.builder()
                .name("Samsung 20S")
                .description("Galaxy 20S Red color")
                .price(1000L)
                .build();
    }

}
