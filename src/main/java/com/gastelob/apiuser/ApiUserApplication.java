package com.gastelob.apiuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class ApiUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiUserApplication.class, args);
    }

}
