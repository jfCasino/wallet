package com.jfCasino.wallet_service.Service;

import java.util.UUID;


import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jfCasino.wallet_service.Enitities.Wallet;
import com.jfCasino.wallet_service.Enitities.WalletCommit;
import com.jfCasino.wallet_service.Enitities.WalletReservation;
import com.jfCasino.wallet_service.Repository.WalletRepository;
import com.jfCasino.wallet_service.Repository.WalletReservationRepository;

import jakarta.transaction.Transactional;

import com.jfCasino.wallet_service.Repository.WalletCommitsRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

//JF Service class je Application Scoped po defaultu
@Service
public class WalletService {

    public final WalletRepository walletRepository;
    public final WalletCommitsRepository walletCommitsRepository;
    public final WalletReservationRepository walletReservationRepository;

    public WalletService(WalletRepository walletRepository, WalletCommitsRepository walletCommitsRepository, WalletReservationRepository walletReservationRepository) {
        this.walletRepository = walletRepository;
        this.walletCommitsRepository = walletCommitsRepository;
        this.walletReservationRepository = walletReservationRepository;
    }
    
    public Wallet getBalance(String userID) {
        //TODO unmock
        /* 
        Wallet wallet = new Wallet();
        wallet.setBalance(42);
        wallet.setUserID(userID);
        */

        //TODO mabye return optional and handle not found in controller/frontend?
        return walletRepository.findByUserID(userID).orElse(new Wallet());
    }

    public Page<Wallet> getTopWallets(String direction, int limit) {
        Sort sort = direction.equalsIgnoreCase("desc")
        ? Sort.by("balance").descending()
        : Sort.by("balance").ascending();

        Pageable pageable = PageRequest.of(0, limit, sort);

        return walletRepository.findAll(pageable); 

        //mock implementation
        /* 
        Wallet wallet = new Wallet();
        wallet.setUserID("1");        
        wallet.setBalance(1000);
        Wallet wallet2 = new Wallet();
        wallet2.setUserID("2");
        wallet2.setBalance(900);
        Wallet wallet3 = new Wallet();
        wallet3.setUserID("3");
        wallet3.setBalance(800);
        return List.of(wallet, wallet2, wallet3); */
    }

    @Transactional
    public WalletReservation createReservation(String userID, int amount) {
        //TODO get balance from DB and check if sufficient
        int balance = walletRepository.findByUserID(userID)
            .map(Wallet::getBalance)
            .orElse(0);

        if(balance <= amount) {
            throw new RuntimeException("Insufficient balance");
        }

        //create reservation
        WalletReservation reservation = new WalletReservation();
        reservation.setUserID(userID);
        reservation.setAmount(amount);

        //save reservation
        walletReservationRepository.save(reservation);

        return reservation;
    }

    @Transactional
    public WalletCommit commit(UUID reservationID, String userID, int amount) {
        //find wallet by userID
        Wallet wallet = walletRepository.findByUserID(userID).orElseThrow(() -> new RuntimeException("Wallet not found"));
        //update balance
        wallet.setBalance(wallet.getBalance() + amount);
        //save wallet //JF save avtomatsko preveri ali more update ali insert
        walletRepository.save(wallet);

        //create commit
        WalletCommit commit = new WalletCommit();
        commit.setReservationID(reservationID);
        commit.setUserID(userID);
        commit.setAmount(amount);
        commit.setNewBalance(wallet.getBalance());
        //save commit
        walletCommitsRepository.save(commit);

        //TODO handle reservation status update

        return commit;

        //mock implementation
        /* 
        WalletCommit commit = new WalletCommit();
        commit.setReservationID(reservationID);
        commit.setUserID(userID); 
        commit.setAmount(amount);
        commit.setNewBalance(42 + amount); 
        
        return commit;
        */
    }   
}
