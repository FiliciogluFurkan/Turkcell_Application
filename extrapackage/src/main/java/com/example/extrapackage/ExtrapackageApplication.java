package com.example.extrapackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ExtrapackageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtrapackageApplication.class, args);
	}

}
