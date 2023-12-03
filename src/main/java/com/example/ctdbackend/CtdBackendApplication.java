package com.example.ctdbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.ctdbackend.entity")
public class CtdBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtdBackendApplication.class, args);
	}

}
