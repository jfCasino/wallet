package com.jfCasino.wallet_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Map;


@EnableFeignClients
@SpringBootApplication()
public class WalletApplication {

	public static void main(String[] args) {
		//SpringApplication.run(WalletApplication.class, args);
		SpringApplication app = new SpringApplication(WalletApplication.class);
        app.setDefaultProperties(Map.of("server.port", "8081"));
        app.run(args);
	}
}
