package com.jim.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class JimCloudConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JimCloudConfigServerApplication.class, args);
    }

}
