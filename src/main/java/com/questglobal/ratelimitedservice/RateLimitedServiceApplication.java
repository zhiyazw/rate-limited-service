package com.questglobal.ratelimitedservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RateLimitedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateLimitedServiceApplication.class, args);
    }

}
