package com.sinosoft.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 使用Eureka做服务发现.
 *建议大家将接口的消费定义，单独抽一个项目出来，后面打成公共的jar
 * 如果你的Feign接口定义跟你的启动类不在一个包名下，还需要制定扫描的包名
 */
@ComponentScan("com.sinosoft")
//组件扫描
@Configuration
//配置控制
@EnableAutoConfiguration
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.sinosoft.feign")
public class OrderSerivceApplication {
  public static void main(String[] args) {
    SpringApplication.run(OrderSerivceApplication.class, args);
  }
}