package com.currency.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitBrakerController {

    @GetMapping(path = "/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodeResponse")
//    @CircuitBreaker(name = "default", fallbackMethod = "hardcodeReponse")
//    @RateLimiter(name = "default")
    @Bulkhead(name = "sample-api")
    public String sampleApi(){
        log.info("Sample API Call Received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
                String.class);

        return "Sample API";
    }

    public String hardcodeResponse(Exception exception){
        return "fallback-response";
    }
}
