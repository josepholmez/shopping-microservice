package com.olmez.coremicro.services.impl;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.olmez.coremicro.currency.CurrencyService;
import com.olmez.coremicro.services.ScheduledService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduledServiceImpl implements ScheduledService {

    private final CurrencyService currencyService;

    /**
     * It allows updating currencies every day at 5:45 PM (17:45)
     * 
     * @throws Exception
     */
    @Scheduled(cron = "0 20 10 * * ?") // 17:45:00 or 5:45:00 PM
    @Override
    @Transactional
    public void dailyUpdateCurrencyData() throws InterruptedException {
        log.info("Currency data are being pulled...");
        try {
            currencyService.update(LocalDate.now().minusMonths(1), LocalDate.now());
            log.info("Pulling currencies data is completed.");
        } catch (IOException e) {
            log.info("Failed to currency data pulling. {}", e.getMessage());
        }
    }

}
