package com.jim.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jim.cloud.mapper", "com.jim.cloud.mapper.extend"})
public class JimCloudOrderClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JimCloudOrderClientApplication.class, args);
    }

}
