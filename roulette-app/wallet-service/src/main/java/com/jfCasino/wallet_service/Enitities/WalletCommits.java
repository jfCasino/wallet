package com.jfCasino.wallet_service.Enitities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wallet_commits")
public class WalletCommits {
    
    @Id
    private String commitID;
    private String reservationID;
    private int userID;
    private int amount;
    private int newBalance;

    public WalletCommits() {}

    //getters and setters
    public String getCommitID() {      
        return commitID;
    }

    public void setCommitID(String commitID) {
        this.commitID = commitID;
    }

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

    public int getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(int newBalance) {
        this.newBalance = newBalance;
    }
}
