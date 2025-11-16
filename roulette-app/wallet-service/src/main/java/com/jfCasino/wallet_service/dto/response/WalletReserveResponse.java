package com.jfCasino.wallet_service.dto.response;

import java.util.UUID;

public class WalletReserveResponse {

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_FAILED = "FAILED";

    private UUID reservationID;
    private String userID;
    private int amount;
    private String status; //e.g., "PENDING", "FAILED"

    public WalletReserveResponse(UUID reservationID, String userID, int amount, String status) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.amount = amount;
        this.status = status;
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

}
