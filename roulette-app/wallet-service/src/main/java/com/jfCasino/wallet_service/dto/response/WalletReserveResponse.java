package com.jfCasino.wallet_service.dto.response;

import java.util.UUID;
import java.time.Instant;

public class WalletReserveResponse {

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_FAILED = "FAILED";

    private UUID reservationID;
    private String userID;
    private int amount;
    private String status; //e.g., "PENDING", "FAILED", "COMPLETED"
    private Instant createdAt;
    private Instant expiresAt;

    public WalletReserveResponse(UUID reservationID, String userID, int amount, String status, Instant createdAt, Instant expiresAt) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public UUID getReservationID() {
        return reservationID;
    }

    public String getUserID() {
        return userID;
    }

    public int getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setReservationID(UUID reservationID) {
        this.reservationID = reservationID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }  

}
