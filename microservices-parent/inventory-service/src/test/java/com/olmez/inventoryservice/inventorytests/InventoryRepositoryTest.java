package com.olmez.inventoryservice.inventorytests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.olmez.inventoryservice.InventoryServiceTestApplication;
import com.olmez.inventoryservice.model.Inventory;
import com.olmez.inventoryservice.repository.InventoryRepository;
import com.olmez.inventoryservice.utility.SourceUtils;

@SpringBootTest(classes = InventoryServiceTestApplication.class)
@ActiveProfiles(SourceUtils.TEST_PROFILE)
@TestPropertySource(SourceUtils.TEST_SOURCE)
class InventoryRepositoryTest {

	@Autowired
	private InventoryRepository repository;

	@Test
	void testFindAll() {
		// arrange
		repository.deleteAll();
		Inventory inventory = new Inventory("Samsung 20S", 8);
		inventory = repository.save(inventory);

		// act
		var list = repository.findAll();

		// assert
		assertThat(list).hasSize(1).contains(inventory);
	}

}
