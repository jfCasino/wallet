package com.jfCasino.wallet_service.dto;

public class WalletResponse {
    private int userID;
    private int balance;

    public WalletResponse() {};

    public WalletResponse(int userID, int balance) {
        this.userID = userID;
        this.balance = balance;
    }

    //getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBalance() {
        return balance;
    }
    
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
