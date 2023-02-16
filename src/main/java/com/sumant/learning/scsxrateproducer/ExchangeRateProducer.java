package com.sumant.learning.scsxrateproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class ExchangeRateProducer {

    private static HashMap<String, Double> exchangeRateDataMap = new HashMap<String, Double> (
            Map.of("USD-GBP", 0.8, "USD-AUD", 1.44, "USD-EUR", 0.93)
    );


    @Bean
    public Supplier<List<ExchangeRateRecord>> rateProducer(){
        return () -> {
            List<ExchangeRateRecord> recordList = exchangeRateDataMap.keySet()
                    .stream()
                    .map(key -> {
                        String[] components = key.split("-");
                        String from = components[0];
                        String to = components[1];
                        Double rate = getRandomizedRate(key);
                        ExchangeRateRecord exRecord = new ExchangeRateRecord(from, to, rate);
                        return exRecord;
                    })
                    .collect(Collectors.toList());
                    log.info("Generated records: " + recordList);
                    return recordList;
        };
    }

    private Double getRandomizedRate(String currencyPair){

        Double exchangeRate = exchangeRateDataMap.get(currencyPair);
        double maxRate = 1.1 * exchangeRate;
        double minRate = 0.9 * exchangeRate;
        Double randomizedRate = ((Math.random() * (maxRate - minRate)) + minRate);

        return randomizedRate;
    }


}
