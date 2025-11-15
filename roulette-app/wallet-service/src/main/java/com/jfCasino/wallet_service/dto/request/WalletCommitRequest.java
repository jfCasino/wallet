package com.jfCasino.wallet_service.dto.request;

public class WalletCommitRequest {
    private String reservationID;
    private String userID;
    private int amount;

    public WalletCommitRequest() {};

    //getters and setters
    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
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
