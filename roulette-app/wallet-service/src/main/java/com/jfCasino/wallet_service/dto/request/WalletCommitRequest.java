package com.jfCasino.wallet_service.dto.request;

public class WalletCommitRequest {
    private String reservationID;
    private int userID;
    private int amount;

    public WalletCommitRequest() {};

    //getters and setters
    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }   

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
