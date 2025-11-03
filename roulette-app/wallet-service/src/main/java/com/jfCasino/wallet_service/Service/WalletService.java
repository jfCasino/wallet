package com.jfCasino.wallet_service.Service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jfCasino.wallet_service.Enitities.WalletCommits;
import com.jfCasino.wallet_service.Enitities.WalletReservation;

@Service
public class WalletService {
    
    public int getBalance(int userID) {
        //mock implementation
        return 42;
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
        commit.setUserID(userID); //mock userID
        commit.setAmount(amount);
        commit.setNewBalance(42 + amount); //mock new balance

        return commit;
    }   
}
