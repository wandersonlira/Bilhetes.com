package com.symplesweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class SymplesWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SymplesWebApplication.class, args);	
	}

}
