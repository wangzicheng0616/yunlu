package com.example.ryservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 瑞云 MyBatis-Plus 服务启动类。
 */
@MapperScan("com.example.ryservice.mapper")
@SpringBootApplication
public class RyMybatisPlusServiceApplication {

    /**
     * 启动 Spring Boot 应用。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(RyMybatisPlusServiceApplication.class, args);
    }
}
