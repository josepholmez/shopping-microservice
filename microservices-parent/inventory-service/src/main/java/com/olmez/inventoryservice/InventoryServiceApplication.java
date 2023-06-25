package com.olmez.inventoryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.olmez.inventoryservice.model.Inventory;
import com.olmez.inventoryservice.repository.InventoryRepository;
import com.olmez.inventoryservice.utility.SourceUtils;

@SpringBootApplication
public class InventoryServiceApplication {

	private Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SourceUtils.setSpringConfigLocation();
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			log.info("Loading data");
			log.info("Inventory Service Application is running!");

			// for test
			Inventory inv = new Inventory();
			inv.setSkuCode("sku1234");
			inv.setQuantity(6);
			inventoryRepository.save(inv);

			Inventory inv2 = new Inventory();
			inv2.setSkuCode("sku567");
			inv2.setQuantity(0);
			inventoryRepository.save(inv2);

		};
	}

}