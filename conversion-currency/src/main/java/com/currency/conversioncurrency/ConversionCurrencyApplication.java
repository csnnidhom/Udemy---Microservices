package com.currency.conversioncurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConversionCurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConversionCurrencyApplication.class, args);
	}

}
