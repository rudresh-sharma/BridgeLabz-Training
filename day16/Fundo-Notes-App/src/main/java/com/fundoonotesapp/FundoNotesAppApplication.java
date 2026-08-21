package com.fundoonotesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FundoNotesAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                FundoNotesAppApplication.class,
                args
        );
    }
}