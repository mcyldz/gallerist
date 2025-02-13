package com.mcyldz.controller;

import com.mcyldz.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

    RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}
