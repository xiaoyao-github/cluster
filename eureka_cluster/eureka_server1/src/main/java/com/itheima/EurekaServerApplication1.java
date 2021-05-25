package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima
 * @date 2020-1-10
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication1 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication1.class,args);
    }
}
