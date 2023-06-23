package com.olmez.productservice.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olmez.productservice.ProductServiceTestApplication;
import com.olmez.productservice.model.ProductRequestModel;
import com.olmez.productservice.repository.ProductRepository;

@SpringBootTest(classes = ProductServiceTestApplication.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository productRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createProduct() throws Exception {
        ProductRequestModel model = getProductRequestModel();
        String modelAsString = objectMapper.writeValueAsString(model);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(modelAsString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        assertThat(productRepository.findAll()).hasSize(1);
    }

    private ProductRequestModel getProductRequestModel() {
        return ProductRequestModel.builder()
                .name("Samsung 20S")
                .description("Galaxy 20S Red color")
                .price(BigDecimal.valueOf(1000))
                .build();
    }

}
