package com.jfCasino.wallet_service.Enitities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    private int userID;
    private int balance;

    public Wallet() {};

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
