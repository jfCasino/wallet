package com.jfCasino.wallet_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfCasino.wallet_service.Enitities.Wallet;
import com.jfCasino.wallet_service.Enitities.WalletCommit;
import com.jfCasino.wallet_service.Enitities.WalletReservation;
import com.jfCasino.wallet_service.Service.WalletService;
import com.jfCasino.wallet_service.dto.request.WalletCommitRequest;
import com.jfCasino.wallet_service.dto.request.WalletReserveRequest;
import com.jfCasino.wallet_service.dto.response.WalletCommitResponse;
import com.jfCasino.wallet_service.dto.response.WalletReserveResponse;
import com.jfCasino.wallet_service.dto.response.WalletResponse;

import java.util.stream.Collectors;
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
    public ResponseEntity<WalletResponse> getWallet(@PathVariable("userID") String userID) {
        Wallet wallet = walletService.getBalance(userID);
        WalletResponse response = new WalletResponse(wallet.getUserID(), wallet.getBalance());
 

        return ResponseEntity.ok(response);
    }

    //JF do te metode bo dostopal le stats service
    @GetMapping("/wallets")
    public ResponseEntity<List<WalletResponse>> getAllWallets(@RequestParam(name = "order",defaultValue = "asc") String order,
    @RequestParam(name = "limit", defaultValue = "10") int limit) {
        //TODO change it so you do not return a List of Etities, but a list od DTOs?
        List<Wallet> wallets = walletService.getTopWallets(order, limit).getContent();
        List<WalletResponse> response = wallets.stream()
            .map(wallet -> new WalletResponse(
            wallet.getUserID(),
            wallet.getBalance()
            )).collect(Collectors.toList());


        return ResponseEntity.ok(response);
    }

    //JF do te metode bo dostopal le rulette service
    //TODO safety
    @PostMapping("/wallets/reserve")
    public ResponseEntity<WalletReserveResponse> postReserve(@RequestBody WalletReserveRequest request) {
        WalletReservation reservation = walletService.createReservation(request.getUserID(), request.getAmount());
        WalletReserveResponse response = new WalletReserveResponse(
            reservation.getReservationID(),
            reservation.getUserID(),
            reservation.getAmount(),
            reservation.getStatus(),
            reservation.getCreatedAt(),
            reservation.getExpiresAt()
        );


        return ResponseEntity.ok(response);
    }

    //JF commit vrne rezervirana sredstva + dobicek
    @PostMapping("/wallets/commit")
    public ResponseEntity<WalletCommitResponse> postCommit(@RequestBody WalletCommitRequest request) {
        WalletCommit commit = walletService.commit(
            request.getReservationID(),
            request.getUserID(),
            request.getAmount()
        );
        
        WalletCommitResponse response = new WalletCommitResponse(
            commit.getCommitID(),
            commit.getReservationID(),
            commit.getUserID(),
            commit.getAmount(),
            commit.getNewBalance(),
            commit.getCreatedAt()
        );
        
        
        return ResponseEntity.ok(response);
    }

}