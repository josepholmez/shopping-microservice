package com.olmez.coremicro.currency;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.olmez.coremicro.model.CurrencyRate;

public interface CurrencyService {

    List<CurrencyRate> checkLastWeek() throws InterruptedException, IOException;

    CurrencyRate update(LocalDate date) throws IOException, InterruptedException;

    List<CurrencyRate> update(LocalDate startDate, LocalDate endDate)
            throws InterruptedException, IOException;

    ////////////////////
    List<CurrencyRate> getAllRates();

    boolean createCurrencyRate(CurrencyRate rate);

    CurrencyRate findCurrencyRateById(Long id);

    CurrencyRate updateCurrencyRate(Long id, CurrencyRate rateDetails);

    boolean deleteCurrencyRate(Long id);

    CurrencyRate findCurrencyRateByDate(LocalDate date);

    Double convert(CurrencyWrapper curWrapper);
}
