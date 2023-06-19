package com.olmez.coremicro.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.olmez.coremicro.CoreMicroTestApplication;
import com.olmez.coremicro.model.CurrencyRate;
import com.olmez.coremicro.utility.TestUtility;

@SpringBootTest(classes = CoreMicroTestApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(TestUtility.TEST_PROFILE)
@TestPropertySource(TestUtility.TEST_SOURCE)
class CurrencyRateRepositoryTest {

	@Autowired
	private CurrencyRateRepository repository;

	@BeforeEach
	void clean() {
		repository.deleteAll();
	}

	@Test
	void testFindAll() {
		// arrange
		var rate = new CurrencyRate();
		rate.setDate(LocalDate.of(2023, 2, 13));
		rate = repository.save(rate);

		var rate2 = new CurrencyRate();
		rate2.setDate(LocalDate.of(2023, 2, 14));
		rate2 = repository.save(rate2);

		var rate3 = new CurrencyRate();
		rate3.setDate(LocalDate.of(2023, 2, 15));
		rate3 = repository.save(rate3);

		// act
		var rates = repository.findAll();

		// assert
		assertThat(rates).hasSize(3);
		assertThat(rates.get(0)).isEqualTo(rate3); // Feb 15
		assertThat(rates.get(1)).isEqualTo(rate2); // Feb 14
		assertThat(rates.get(2)).isEqualTo(rate);// Feb 13

	}

}
