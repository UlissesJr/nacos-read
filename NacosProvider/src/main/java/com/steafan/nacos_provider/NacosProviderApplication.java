package com.steafan.nacos_provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@MapperScan("com.steafan.nacos_provider.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(NacosProviderApplication.class).run(args);
        System.out.println("nacos-service-provider launch successfully.");
    }



}
