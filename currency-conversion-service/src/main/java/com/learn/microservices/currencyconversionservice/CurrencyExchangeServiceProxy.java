package com.learn.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url="localhost:8000")  // to be used without Ribbon Load balancer

//@FeignClient(name="currency-exchange-service") // to be used with ribbon and naming server
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")  // to be used with ribbon and naming server and with zuul as well
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")   // to be used with zuul
    //@GetMapping("/currency-exchange/from/{from}/to/{to}")    //to be used with or without ribbon and naming server
    public CurrencyConversionBean retrieveExchangeValue
            (@PathVariable("from") String from, @PathVariable("to") String to);
}
