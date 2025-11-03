package com.jfCasino.wallet_service.Service;

import java.util.UUID;

import org.springframework.stereotype.Service;

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
}
