package com.microservices.limitservices.controller;

import com.microservices.limitservices.bean.Limits;
import com.microservices.limitservices.configuration.LimitsConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsController {

    private final LimitsConfiguration limitsConfiguration;

    @GetMapping(
            path = "/limits"
    )
    public Limits limits(){
        return new Limits(limitsConfiguration.getMinimum(), limitsConfiguration.getMaximum());
//        return new Limits(1,100);
    }
}
