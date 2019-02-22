package com.example.testzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TestzuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestzuulApplication.class, args);
	}

}

