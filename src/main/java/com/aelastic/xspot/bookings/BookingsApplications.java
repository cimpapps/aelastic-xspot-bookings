package com.aelastic.xspot.bookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableKafka
@EnableAsync
public class BookingsApplications {

    public static void main(String[] args) {
        SpringApplication.run(BookingsApplications.class, args);
    }

}

