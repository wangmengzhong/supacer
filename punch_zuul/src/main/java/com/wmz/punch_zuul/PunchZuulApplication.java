package com.wmz.punch_zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy 
@SpringCloudApplication
public class PunchZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(PunchZuulApplication.class, args);
	}
	
	
}
