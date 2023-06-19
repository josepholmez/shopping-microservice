package com.olmez.coremicro.services;

import java.io.IOException;

public interface ScheduledService {

    void dailyUpdateCurrencyData() throws IOException, InterruptedException;

}
