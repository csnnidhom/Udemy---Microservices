package com.currency.currencyexchangeservice.controller;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import com.currency.currencyexchangeservice.entity.Exchange;
import com.currency.currencyexchangeservice.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExchanceCurrencyController {

    private final Environment environment;

    private final ExchangeRepository exchangeRepository;

    @GetMapping(
            path = "/currency-exchange/from/{from}/to/{to}"
    )
    public Exchange exchangeResponse(
            @PathVariable String from,
            @PathVariable String to
    ){

        log.info("retreiveExchangeValue called with {} to {}", from, to);

        Exchange exchangeResponse = exchangeRepository.findByFromAndTo(from, to);

        if (exchangeResponse == null){
            throw new RuntimeException("Unable to Find data from " + from + " to " + to);
        }

        String port = environment.getProperty("server.port");
        exchangeResponse.setEnvironment(port);

        return exchangeResponse;
    }


}
