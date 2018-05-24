package com.learn.microservices.limitsservice;


import com.learn.microservices.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfigurations() {
        return new  LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
    }


    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackretrieveConfigurations")
    public LimitConfiguration retrieveConfigurations() {
        throw new RuntimeException("Not Available");
    }

    public LimitConfiguration fallbackretrieveConfigurations() {
        return new LimitConfiguration(999,9);
    }
}
