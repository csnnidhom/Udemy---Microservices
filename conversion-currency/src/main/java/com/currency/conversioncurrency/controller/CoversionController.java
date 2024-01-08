package com.currency.conversioncurrency.controller;

import com.currency.conversioncurrency.config.RestTemplateConfiguration;
import com.currency.conversioncurrency.model.CurrencyConversionResponse;
import com.currency.conversioncurrency.proxyOpenFeign.CurrencyExchangeProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class CoversionController {

    private final CurrencyExchangeProxy currencyExchangeProxy;

    private final RestTemplate restTemplate;

    @GetMapping(path = "/currency-conversion-rest-template/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
            ){

        HashMap<String, String> uriVariabel = new HashMap<>();
        uriVariabel.put("from", from);
        uriVariabel.put("to", to);

        ResponseEntity<CurrencyConversionResponse> responseEntity = restTemplate.getForEntity(
                "http://localhost:8282/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionResponse.class,
                uriVariabel
        );

        CurrencyConversionResponse currencyConversionResponse = responseEntity.getBody();


        return new CurrencyConversionResponse(
                currencyConversionResponse.getId(), from, to, quantity,
                currencyConversionResponse.getConversionMultiple(),
                quantity.multiply(currencyConversionResponse.getConversionMultiple()),
                currencyConversionResponse.getEnvironment() + " rest-template");
    }

    @GetMapping(path = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){

        CurrencyConversionResponse currencyConversionResponse = currencyExchangeProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversionResponse(
                currencyConversionResponse.getId(), from, to, quantity,
                currencyConversionResponse.getConversionMultiple(),
                quantity.multiply(currencyConversionResponse.getConversionMultiple()),
                currencyConversionResponse.getEnvironment() + " feign");
    }
}
