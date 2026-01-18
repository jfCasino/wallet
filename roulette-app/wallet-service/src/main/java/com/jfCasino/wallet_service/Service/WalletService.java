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
import java.math.BigDecimal;

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
 
        return walletRepository.findByUserID(userID).orElse(new Wallet());
    }

    @Transactional
    public UUID createWallet(String userID) {
        Wallet wallet = new Wallet();
        wallet.setUserID(userID);
        wallet.setBalance(BigDecimal.valueOf(500)); // new wallets start with balance 500
        wallet = walletRepository.save(wallet);
        return wallet.getWalletID();
    }

    public Page<Wallet> getTopWallets(String direction, int limit) {
        Sort sort = direction.equalsIgnoreCase("desc")
        ? Sort.by("balance").descending()
        : Sort.by("balance").ascending();

        Pageable pageable = PageRequest.of(0, limit, sort);


        return walletRepository.findAll(pageable); 
    }

    @Transactional
    public WalletReservation createReservation(String userID, BigDecimal amount) {
        //TODO get balance from DB and check if sufficient
        Wallet wallet = walletRepository.findByUserID(userID).orElse(null);

        //create reservation
        WalletReservation reservation = new WalletReservation();
        reservation.setUserID(userID);
        reservation.setAmount(amount);
        
        //sporoči če denarnica ne obstaja
        if (wallet == null) {
            reservation.setStatus(WalletReservation.STATUS_INVALID_WALLET);
            walletReservationRepository.save(reservation);
            return reservation;
        }

        //JF če obstaja preveri bilanco
        BigDecimal balance = walletRepository.findByUserID(userID)
            .map(Wallet::getBalance)
            .orElse(BigDecimal.ZERO);

        //premalo sredstev
        if(balance.compareTo(amount) < 0) {
            reservation.setStatus(WalletReservation.STATUS_FAILED);
            walletReservationRepository.save(reservation);
            return reservation;
        }

        //odstej sredstva če jih je dovolj
        wallet.setBalance(balance.subtract(amount));

        //save reservation and wallet
        walletReservationRepository.save(reservation);
        walletRepository.save(wallet);

        
        return reservation;
    }

    @Transactional
    public WalletCommit commit(UUID reservationID, String userID, BigDecimal amount) {
        //find wallet by userID
        Wallet wallet = walletRepository.findByUserID(userID).orElseThrow(() -> new RuntimeException("Wallet not found"));
        //update balance
        wallet.setBalance(wallet.getBalance().add(amount));
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

        //handle reservation status update
        WalletReservation reservation = walletReservationRepository.findById(reservationID)
            .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus("COMMITTED");
        walletReservationRepository.save(reservation);


        return commit;
    }   
}
