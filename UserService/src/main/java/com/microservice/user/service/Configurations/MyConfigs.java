package com.microservice.user.service.Configurations;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfigs {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
       return new RestTemplate();
    }
}
