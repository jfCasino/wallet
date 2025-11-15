package com.jfCasino.wallet_service.dto.request;

public class WalletReserveRequest {
    private String userID;
    private int amount;

    public WalletReserveRequest() {};
    //getters and setters
    
    public int getAmount() {
        return amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
