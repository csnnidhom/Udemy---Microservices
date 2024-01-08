package com.currency.conversioncurrency.proxyOpenFeign;

import com.currency.conversioncurrency.model.CurrencyConversionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    @GetMapping(
            path = "/currency-exchange/from/{from}/to/{to}"
    )
    public CurrencyConversionResponse retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);

}
