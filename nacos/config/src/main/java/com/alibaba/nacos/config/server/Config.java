/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Config main.
 *
 * @author Nacos
 */
@EnableScheduling
// scanBasePackages 属性指定了需要扫描的包。
// 其中，com.alibaba.nacos.config.server 包和 com.alibaba.nacos.core 包分别是 Nacos 配置服务器和核心模块的包，
// 这个注解的作用是将 Nacos 的配置服务器和核心模块注册为 Spring Bean，从而可以在应用程序中使用这些组件。

// 配置 scanBasePackages 属性和使用 @ComponentScan 注解的 basePackages 属性是等效的，
// 都是用于指定 Spring 扫描的包范围。
// scanBasePackages 属性会被转化为 @ComponentScan 注解的 basePackages 属性

// @SpringBootApplication(scanBasePackages = {"com.example.demo"})
// @ComponentScan(basePackages = {"com.example.demo"})

@SpringBootApplication(scanBasePackages = {
        "com.alibaba.nacos.config.server",
        "com.alibaba.nacos.core"})

public class Config {
    
    public static void main(String[] args) {
        SpringApplication.run(Config.class, args);
    }
}
