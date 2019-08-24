package com.xlyd.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// 开启eureka server
@EnableEurekaServer
@SpringBootApplication
public class SpringbootEurekaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEurekaDemoApplication.class, args);
	}

}
