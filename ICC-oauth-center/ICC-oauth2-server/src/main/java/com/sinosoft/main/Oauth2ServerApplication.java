package com.sinosoft.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.sinosoft")
//组件扫描
@Configuration
//配置控制
@EnableAutoConfiguration
@EnableDiscoveryClient
public class Oauth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2ServerApplication.class, args);
//$2a$10$6lIOO2/uSAjzEP37.X1qD.orufLsKXQQ4JIsimxPrNpvmpCPuRnCS
//		System.out.printf(new BCryptPasswordEncoder().encode("123456"));
	}
//	@Bean
//	public OAuth2RestOperations oAuth2RestOperations(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
//		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(details, oauth2ClientContext);
//		return oAuth2RestTemplate;
//	}
}
