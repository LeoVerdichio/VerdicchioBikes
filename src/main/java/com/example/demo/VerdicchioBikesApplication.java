package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class VerdicchioBikesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerdicchioBikesApplication.class, args);
	}

}
