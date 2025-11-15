package com.jfCasino.wallet_service.dto.request;

public class WalletReserveRequest {
    private int userID;
    private int amount;

    public WalletReserveRequest() {};
    //getters and setters
    
    public int getAmount() {
        return amount;
    }

    public int getUserID() {
        return userID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
