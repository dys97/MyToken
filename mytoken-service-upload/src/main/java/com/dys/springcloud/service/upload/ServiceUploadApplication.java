package com.dys.springcloud.service.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUploadApplication.class, args);
	}

}
