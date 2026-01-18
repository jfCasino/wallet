package com.jfCasino.wallet_service.Enitities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;
import java.math.BigDecimal;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "wallet_commits")
@EntityListeners(AuditingEntityListener.class)
public class WalletCommit {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID commitID;

    @Column(nullable = false, unique = true)
    private UUID reservationID;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal newBalance;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public WalletCommit() {}

    //getters and setters
    public UUID getCommitID() {
        return commitID;
    }

    public UUID getReservationID() {
        return reservationID;
    }

    public void setReservationID(UUID reservationID) {
        this.reservationID = reservationID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}
