package com.jim.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan(basePackages = {"com.jim.cloud.mapper", "com.jim.cloud.mapper.extend"})
@EnableEurekaClient
@EnableCircuitBreaker
public class JimCloudOrderClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JimCloudOrderClientApplication.class, args);
    }

}
