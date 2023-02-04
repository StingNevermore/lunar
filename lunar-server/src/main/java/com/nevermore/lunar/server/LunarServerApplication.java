package com.nevermore.lunar.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author nevermore
 */
@SpringBootApplication(scanBasePackages = "com.nevermore.lunar")
public class LunarServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunarServerApplication.class);
    }

}
