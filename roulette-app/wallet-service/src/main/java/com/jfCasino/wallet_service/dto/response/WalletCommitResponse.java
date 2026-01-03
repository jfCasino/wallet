package com.jfCasino.wallet_service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(
    name = "WalletCommitResponse",
    description = "Response returned after committing a wallet transaction"
)
public class WalletCommitResponse {

    @Schema(
        description = "Unique identifier of the commit operation",
        example = "7e57d004-2b97-0e7a-b45f-5387367791cd"
    )
    private UUID commitID;

    @Schema(
        description = "Identifier of the wallet reservation that was committed",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    private UUID reservationID;

    @Schema(
        description = "Unique identifier of the user whose wallet was affected",
        example = "user-12345"
    )
    private String userID;

    @Schema(
        description = "Amount charged from the wallet",
        example = "150"
    )
    private int amount;

    @Schema(
        description = "New wallet balance after the commit",
        example = "850"
    )
    private int newBalance;

    @Schema(
        description = "Timestamp when the commit was created",
        example = "2026-01-03T10:20:30Z"
    )
    private Instant createdAt;

    public WalletCommitResponse(UUID commitID, UUID reservationID, String userID, int amount, int newBalance, Instant createdAt) {
        this.commitID = commitID;
        this.reservationID = reservationID;
        this.userID = userID;
        this.amount = amount;
        this.newBalance = newBalance;
        this.createdAt = createdAt;
    }

    //getters and setters
    public UUID getCommitID() {
        return commitID;
    }

    public void setCommitID(UUID commitID) {
        this.commitID = commitID;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
