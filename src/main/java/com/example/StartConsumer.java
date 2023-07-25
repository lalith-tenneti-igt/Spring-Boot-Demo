package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={
		"com.example.controller", "com.example.service", "com.example.model", "com.example.config"})
@ComponentScan({"com.example.*"})
public class StartConsumer {

	public static void main(String[] args) {
		SpringApplication.run(StartConsumer.class, args);
	}

}
