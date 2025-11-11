package com.jfCasino.wallet_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfCasino.wallet_service.Enitities.Wallet;
import com.jfCasino.wallet_service.Enitities.WalletCommits;
import com.jfCasino.wallet_service.Enitities.WalletReservation;
import com.jfCasino.wallet_service.Service.WalletService;
import com.jfCasino.wallet_service.dto.WalletCommitRequest;
import com.jfCasino.wallet_service.dto.WalletReserveRequest;

import java.util.Map;
import java.util.List;


@RestController
public class WalletController {

    private final WalletService walletService;

    //Constructor injection
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    //TODO rename api endpoints
    @GetMapping("/wallets/{userID}")
    public ResponseEntity<Map<String, Object>> getWallet(@PathVariable("userID") int userID) {
        Wallet wallet = walletService.getBalance(userID);
 
        return ResponseEntity.ok(Map.of(
            "userID", wallet.getUserID(),
            "balance", wallet.getBalance()
        ));
    }

    //JF do te metode bo dostopal le rulette service
    @GetMapping("/wallets")
    public ResponseEntity<List<Wallet>> getAllWallets(@RequestParam(name = "order",defaultValue = "asc") String order,
    @RequestParam(name = "limit", defaultValue = "10") int limit) {
        //TODO change it so you do not return a List of Etities, but a list od DTOs?
        return ResponseEntity.ok(walletService.getTopWallets(order, limit));
    }

    //JF do te metode bo dostopal le rulette service
    //TODO safety
    @PostMapping("/wallets/reserve")
    public ResponseEntity<Map<String,Object>> postReserve(@RequestBody WalletReserveRequest request) {
        WalletReservation reservation = walletService.createReservation(request.getUserID(), request.getAmount());

        //JF vrni podatke o rezervaciji
        return ResponseEntity.ok(Map.of(
            "reservationID", reservation.getReservationID(),
            "userID", reservation.getUserID(),
            "amount", reservation.getAmount(),
            "status", reservation.getStatus()  
        ));
    }

    //JF commit vrne rezervirana sredstva + dobicek
    @PostMapping("/wallets/commit")
    public ResponseEntity<Map<String,Object>> postCommit(@RequestBody WalletCommitRequest request) {
        WalletCommits commit = walletService.commit(
            request.getReservationID(),
            request.getUserID(),
            request.getAmount()
        );  
        
        
        return ResponseEntity.ok(Map.of(
            "reservationID", commit.getReservationID(),
            "userID", commit.getUserID(),
            "amount", commit.getAmount(),
            "newBalance", commit.getNewBalance(),
            "status", "COMMITTED"
        ));
    }

}