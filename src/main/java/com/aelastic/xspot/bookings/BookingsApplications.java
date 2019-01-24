package com.aelastic.xspot.bookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BookingsApplications {

    public static void main(String[] args) {
        SpringApplication.run(BookingsApplications.class, args);
    }

}

