package com.olmez.productservice.producttests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.olmez.productservice.ProductServiceTestApplication;
import com.olmez.productservice.model.Product;
import com.olmez.productservice.repository.ProductRepository;
import com.olmez.productservice.utility.SourceUtils;

@SpringBootTest(classes = ProductServiceTestApplication.class)
@ActiveProfiles(SourceUtils.TEST_PROFILE)
@TestPropertySource(SourceUtils.TEST_SOURCE)
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
