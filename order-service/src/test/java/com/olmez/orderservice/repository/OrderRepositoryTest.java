package com.olmez.orderservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.olmez.orderservice.OrderServiceTestApplication;
import com.olmez.orderservice.model.Order;
import com.olmez.orderservice.utility.TestUtility;

@SpringBootTest(classes = OrderServiceTestApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(TestUtility.TEST_PROFILE)
@TestPropertySource(TestUtility.TEST_SOURCE)
class OrderRepositoryTest {

	@Autowired
	private OrderRepository repository;

	@Test
	void testFindAll() {
		// arrange
		// repository.deleteAll();
		Order order = new Order("Samsung 20S", "Galaxy 20S Red color", 2000L);
		order = repository.save(order);

		// act
		var list = repository.findAll();

		// assert
		assertThat(list).hasSize(1).contains(order);
	}

}
