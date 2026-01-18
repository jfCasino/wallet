package com.jfCasino.wallet_service.Enitities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


import java.time.Instant;
import java.util.UUID;
import java.math.BigDecimal;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "wallet_reservations")
@EntityListeners(AuditingEntityListener.class)
public class WalletReservation {

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_FAILED = "FAILED";
    public static final String STATUS_INVALID_WALLET = "INVALID_WALLET";

    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID reservationID;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant expiresAt;

    public WalletReservation() {}

    // Automatically set expiresAt BEFORE insert:
    @PrePersist
    private void onCreate() {
        // createdAt may not be set yet by auditing,
        // so use Instant.now() instead
        this.createdAt = Instant.now();
        this.expiresAt = this.createdAt.plusSeconds(5 * 60); // +5 minutes
        this.status = STATUS_PENDING;
    }

    //getters and setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

}
