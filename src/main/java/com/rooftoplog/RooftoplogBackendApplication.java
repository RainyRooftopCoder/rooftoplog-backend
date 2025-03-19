package com.rooftoplog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RooftoplogBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(RooftoplogBackendApplication.class, args);

        log.info("🎇🎆 Start Server! Enjoy Rooftop Log😁😁 🎇🎆");
    }

}
