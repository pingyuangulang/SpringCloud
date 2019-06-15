package com.jim.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.jim.cloud.mapper", "com.jim.cloud.mapper.extend"})
public class JimCloudUserClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JimCloudUserClientApplication.class, args);
    }

}
