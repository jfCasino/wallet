package com.jfCasino.wallet_service.Enitities;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wallet")
@EntityListeners(AuditingEntityListener.class)
public class Wallet {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID walletID;

    @Column(nullable = false, unique = true)
    private String userID; 

    @Column(nullable = false)
    private int balance = 0;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    //JF avtomatsko posodobi ob vsaki spremembi
    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;


    public Wallet() {};

    //getters and setters
    public UUID getWalletID () {
        return walletID;
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
