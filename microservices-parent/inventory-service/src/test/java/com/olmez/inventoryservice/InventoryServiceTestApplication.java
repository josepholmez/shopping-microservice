package com.olmez.inventoryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;

import com.olmez.inventoryservice.utility.SourceUtils;

@SpringBootApplication
@TestPropertySource(SourceUtils.TEST_SOURCE)
public class InventoryServiceTestApplication {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceTestApplication.class, args);
	}

	@Bean
	@Profile(SourceUtils.TEST_PROFILE)
	CommandLineRunner init() {
		return args -> {
			log.info("Inventory Service TEST Application is running!");
		};
	}
}
