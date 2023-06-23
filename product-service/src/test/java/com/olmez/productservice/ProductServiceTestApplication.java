package com.olmez.productservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.olmez.productservice.utility.TestUtility;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Testcontainers
@AutoConfigureMockMvc
@Slf4j
public class ProductServiceTestApplication {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceTestApplication.class, args);
	}

	@Bean
	@Profile(TestUtility.TEST_PROFILE)
	CommandLineRunner loadData() {
		return args -> {
			log.info("Product Service TEST Application is running!");
			log.info("Product Service TEST Application is running!");
		};
	}

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
}
