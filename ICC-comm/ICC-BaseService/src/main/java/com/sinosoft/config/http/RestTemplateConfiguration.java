package com.sinosoft.config.http;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfiguration {
    /**
     * loadbalanced是使用ribbon对集群的服务进行调用 可以在这配置策略
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTmp() {
        return new RestTemplate();
    }


}
