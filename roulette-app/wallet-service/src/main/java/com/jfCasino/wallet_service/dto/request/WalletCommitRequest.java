package com.jfCasino.wallet_service.dto.request;

import java.util.UUID;

public class WalletCommitRequest {
    private UUID reservationID;
    private String userID;
    private int amount;

    public WalletCommitRequest() {};

    //getters and setters
    public UUID getReservationID() {
        return reservationID;
    }

    public void setReservationID(UUID reservationID) {
        this.reservationID = reservationID;
    }   

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
