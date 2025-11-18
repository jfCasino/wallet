package com.jfCasino.wallet_service.dto.response;

import java.time.Instant;
import java.util.UUID;


public class WalletCommitResponse {
    private UUID commitID;
    private UUID reservationID;
    private String userID;
    private int amount;
    private int newBalance;
    private Instant createdAt;

    public WalletCommitResponse(UUID commitID, UUID reservationID, String userID, int amount, int newBalance, Instant createdAt) {
        this.commitID = commitID;
        this.reservationID = reservationID;
        this.userID = userID;
        this.amount = amount;
        this.newBalance = newBalance;
        this.createdAt = createdAt;
    }

    //getters and setters
    public UUID getCommitID() {
        return commitID;
    }

    public void setCommitID(UUID commitID) {
        this.commitID = commitID;
    }

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

    public int getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(int newBalance) {
        this.newBalance = newBalance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
