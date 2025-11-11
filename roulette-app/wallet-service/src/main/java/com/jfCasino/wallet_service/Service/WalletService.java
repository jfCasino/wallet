package com.jfCasino.wallet_service.Service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jfCasino.wallet_service.Enitities.Wallet;
import com.jfCasino.wallet_service.Enitities.WalletCommits;
import com.jfCasino.wallet_service.Enitities.WalletReservation;

//JF Service class je Application Scoped po defaultu
@Service
public class WalletService {
    
    public Wallet getBalance(int userID) {
        //TODO unmock
        Wallet wallet = new Wallet();
        wallet.setBalance(42);
        wallet.setUserID(userID);
        return wallet;
    }

    public List<Wallet> getTopWallets(String order, int limit) {
        //TODO implement method to get top wallets from DB with order and limit

        //mock implementation
        Wallet wallet = new Wallet();
        wallet.setUserID(1);        
        wallet.setBalance(1000);
        Wallet wallet2 = new Wallet();
        wallet2.setUserID(2);
        wallet2.setBalance(900);
        Wallet wallet3 = new Wallet();
        wallet3.setUserID(3);
        wallet3.setBalance(800);
        return List.of(wallet, wallet2, wallet3);
    }

    public WalletReservation createReservation(int userID, int amount) {
        //TODO get balance from DB and check if sufficient

        //mock implementation
        WalletReservation reservation = new WalletReservation();
        reservation.setReservationID(UUID.randomUUID().toString()); //unique ID
        reservation.setUserID(userID);
        reservation.setAmount(amount);
        reservation.setStatus("PENDING");
        return reservation;
    }

    public WalletCommits commit(String reservationID, int userID, int amount) {
        //TODO close the reservation in DB, update balance, calculate new balance

        //mock implementation
        WalletCommits commit = new WalletCommits();
        commit.setCommitID(UUID.randomUUID().toString());
        commit.setReservationID(reservationID);
        commit.setUserID(userID); 
        commit.setAmount(amount);
        commit.setNewBalance(42 + amount); 

        return commit;
    }   
}
