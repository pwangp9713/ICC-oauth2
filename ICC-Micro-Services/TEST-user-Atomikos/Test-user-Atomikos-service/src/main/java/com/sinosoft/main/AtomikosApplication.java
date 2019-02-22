package com.sinosoft.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 使用做服务 用来注册.
 */
@ComponentScan("com.sinosoft")
//组件扫描
@Configuration
//配置控制
@EnableAutoConfiguration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableEurekaClient
public class AtomikosApplication {
    public static void main(String[] args) {
        SpringApplication.run(AtomikosApplication.class, args);
    }
}
