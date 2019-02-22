package com.example.givemetoken;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.example.givemetoken.mapper")
public class GivemetokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(GivemetokenApplication.class, args);
    }

}
