package com.jfCasino.wallet_service.dto.response;

import java.util.UUID;

public class WalletResponse {
    private UUID walletID;
    private String userID;
    private int balance;

    public WalletResponse() {};

    public WalletResponse(UUID walletID, String userID, int balance) {
        this.walletID = walletID;
        this.userID = userID;
        this.balance = balance;
    }

    //getters and setters
    public UUID getWalletID () {
        return walletID;
    }

    public void setWalletID (UUID walletID) {
        this.walletID = walletID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getBalance() {
        return balance;
    }
    
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
