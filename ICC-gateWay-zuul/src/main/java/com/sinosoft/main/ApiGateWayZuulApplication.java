package com.sinosoft.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zoure on 2018/5/15.
 */
//@ComponentScan("com.sinosoft")
////组件扫描
//@Configuration
////配置控制
//@EnableAutoConfiguration
//@EnableEurekaClient
//@EnableZuulProxy
//@EnableOAuth2Sso
@EnableZuulProxy
@EnableEurekaClient
@ComponentScan("com.sinosoft")
//组件扫描
@Configuration
//配置控制
@EnableAutoConfiguration
public class ApiGateWayZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayZuulApplication.class, args);
    }
}
