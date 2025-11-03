package com.jfCasino.wallet_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//TODO remove exclude when db is configured
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletApplication.class, args);
	}
}
