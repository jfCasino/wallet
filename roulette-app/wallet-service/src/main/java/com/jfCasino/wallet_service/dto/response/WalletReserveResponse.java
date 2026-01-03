package com.jfCasino.wallet_service.dto.response;

import java.util.UUID;
import java.time.Instant;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "WalletReserveResponse",
    description = "Response returned after reserving funds in a wallet"
)
public class WalletReserveResponse {

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_FAILED = "FAILED";

    @Schema(
        description = "Unique identifier of the reservation",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    private UUID reservationID;

    @Schema(
        description = "Unique identifier of the user whose funds are reserved",
        example = "user-12345"
    )
    private String userID;

    @Schema(
        description = "Amount of funds reserved",
        example = "150"
    )
    private int amount;

    @Schema(
        description = "Current status of the reservation",
        example = "PENDING",
        allowableValues = { "PENDING", "FAILED", "COMPLETED" }
    )
    private String status;

    @Schema(
        description = "Timestamp when the reservation was created",
        example = "2026-01-03T10:15:30Z"
    )
    private Instant createdAt;

    @Schema(
        description = "Timestamp when the reservation expires",
        example = "2026-01-03T10:45:30Z"
    )
    private Instant expiresAt;

    public WalletReserveResponse(UUID reservationID, String userID, int amount, String status, Instant createdAt, Instant expiresAt) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public UUID getReservationID() {
        return reservationID;
    }

    public String getUserID() {
        return userID;
    }

    public int getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setReservationID(UUID reservationID) {
        this.reservationID = reservationID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }  

}
