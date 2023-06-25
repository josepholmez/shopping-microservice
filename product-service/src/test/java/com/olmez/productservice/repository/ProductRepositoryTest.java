package com.olmez.productservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.olmez.productservice.ProductServiceTestApplication;
import com.olmez.productservice.model.Product;
import com.olmez.productservice.utility.TestUtility;

@SpringBootTest(classes = ProductServiceTestApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(TestUtility.TEST_PROFILE)
@TestPropertySource(TestUtility.TEST_SOURCE)
class ProductRepositoryTest {

	@Autowired
	private ProductRepository repository;

	@Test
	void testFindAll() {
		// arrange
		repository.deleteAll();
		Product product = new Product("Samsung 20S", "Galaxy 20S Red color", 1000L);
		product = repository.save(product);

		// act
		var list = repository.findAll();

		// assert
		assertThat(list).hasSize(1).contains(product);
	}

}
