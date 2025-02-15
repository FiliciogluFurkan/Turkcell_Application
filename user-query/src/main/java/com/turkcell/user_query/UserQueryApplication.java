package com.turkcell.user_query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserQueryApplication.class, args);
	}

}
