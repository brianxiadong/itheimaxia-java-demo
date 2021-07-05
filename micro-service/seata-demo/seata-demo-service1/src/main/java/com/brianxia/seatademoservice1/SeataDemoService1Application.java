package com.brianxia.seatademoservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SeataDemoService1Application {

    public static void main(String[] args) {
        SpringApplication.run(SeataDemoService1Application.class, args);
    }

}
