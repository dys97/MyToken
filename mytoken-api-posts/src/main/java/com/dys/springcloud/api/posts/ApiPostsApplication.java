package com.dys.springcloud.api.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients("com.dys.springcloud.api.posts")
@ComponentScan("com.dys.springcloud.api.posts")
public class ApiPostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPostsApplication.class, args);
	}

}
