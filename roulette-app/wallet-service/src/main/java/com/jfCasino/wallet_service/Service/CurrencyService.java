package com.jfCasino.wallet_service.Service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
    

@Service
public class CurrencyService {

    private final RestTemplate restTemplate;

    @Value("${exchange-rate.api-key}")
    private String apiKey;

    @Value("${exchange-rate.base-url}")
    private String baseUrl;

    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Converts an amount from EUR to JPY using ExchangeRate-API.
     */
    public double convertEurToJpy(double amount) {

        String url = String.format(
                "%s/%s/pair/EUR/JPY",
                baseUrl,
                apiKey
        );

        Map<String, Object> response =
                restTemplate.getForObject(url, Map.class);

        double rate = ((Number) response.get("conversion_rate")).doubleValue();

        return amount * rate;
    }
}

