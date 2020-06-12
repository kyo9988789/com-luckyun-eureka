package com.luckyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 程序入口
 * 
 * 2019年05月23日,下午17:52 
 * {@link com.luckyun.Application}
 * 
 * @author yangj080
 * @version 1.0.0
 */
@SpringBootApplication
@EnableEurekaServer
public class Application{
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
	
}
