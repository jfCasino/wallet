package com.jfCasino.wallet_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;



@EnableFeignClients
@SpringBootApplication()
public class WalletApplication {

	public static void main(String[] args) {
		//SpringApplication.run(WalletApplication.class, args);
		SpringApplication app = new SpringApplication(WalletApplication.class);
        app.run(args);
	}
}
