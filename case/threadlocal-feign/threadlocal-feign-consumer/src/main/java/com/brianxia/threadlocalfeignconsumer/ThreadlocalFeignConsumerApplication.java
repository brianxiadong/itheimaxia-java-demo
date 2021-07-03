package com.brianxia.threadlocalfeignconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@ServletComponentScan
public class ThreadlocalFeignConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadlocalFeignConsumerApplication.class, args);
    }

}
