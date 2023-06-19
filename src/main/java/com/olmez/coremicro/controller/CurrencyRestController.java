package com.olmez.coremicro.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.coremicro.currency.CurrencyService;
import com.olmez.coremicro.currency.CurrencyWrapper;
import com.olmez.coremicro.model.CurrencyRate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rates")
@CrossOrigin(origins = "http://localhost:4200") // This allows to talk to port:5000 (ui-backend)
@RequiredArgsConstructor
public class CurrencyRestController {

    private final CurrencyService service;

    private DecimalFormat df = new DecimalFormat("#.####");

    // CREATE
    @PostMapping()
    public boolean createCurrencyRate(@RequestBody CurrencyRate rate) {
        return service.createCurrencyRate(rate);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CurrencyRate> updateCurrencyRate(@PathVariable Long id,
            @RequestBody CurrencyRate rateDetails) {
        CurrencyRate updatedRate = service.updateCurrencyRate(id, rateDetails);
        return ResponseEntity.ok(updatedRate);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCurrencyRate(@PathVariable Long id) {
        Boolean result = service.deleteCurrencyRate(id);
        return ResponseEntity.ok(result);
    }

    /////////////////////////////////////////////////////////////////////////////
    // GET All
    @GetMapping()
    public ResponseEntity<List<CurrencyRate>> getAllRates() {
        List<CurrencyRate> list = service.getAllRates();
        return ResponseEntity.ok(list);
    }

    // GET By Id
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyRate> getRateById(@PathVariable Long id) {
        CurrencyRate rate = service.findCurrencyRateById(id);
        return ResponseEntity.ok(rate);
    }

    // GET By Date
    @GetMapping("/date/{date}")
    public ResponseEntity<CurrencyRate> getCurrencyRateByDate(
            @PathVariable("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        CurrencyRate rate = service.findCurrencyRateByDate(date);
        return ResponseEntity.ok(rate);
    }

    // POST
    @PostMapping("/convert")
    public ResponseEntity<String> getConvertedAmount(@RequestBody CurrencyWrapper curWrapper) {
        Double result = service.convert(curWrapper);
        String strRes = df.format(result);
        return ResponseEntity.ok(strRes);
    }

    // GET
    @GetMapping("/update/last")
    public void updateRatesByLastMonth() throws InterruptedException, IOException {
        service.update(LocalDate.now().minusMonths(1), LocalDate.now());
    }

}