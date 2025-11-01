package com.jfCasino.wallet_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WalletController {

    @GetMapping("/wallet")
    public Map<String, Object> getWallet() {
        return Map.of("balance", 42);
    }
}