package com.example.package_query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PackageQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PackageQueryApplication.class, args);
	}

}
