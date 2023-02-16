package com.sumant.learning.scsxrateproducer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeRateRecord {

    String fromCurrency;
    String toCurrency;
    Double rate;
}
