package com.dys.springcloud.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.dys.springcloud")
@EnableEurekaClient
@MapperScan(basePackages = {"com.dys.springcloud.service.posts.mapper"})
public class ServicePostsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServicePostsApplication.class, args);
	}
}
