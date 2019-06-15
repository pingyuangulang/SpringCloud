package com.jim.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan(basePackages = {"com.jim.cloud.mapper", "com.jim.cloud.mapper.extend"})
@EnableEurekaClient
public class JimCloudUserClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JimCloudUserClientApplication.class, args);
    }

}
