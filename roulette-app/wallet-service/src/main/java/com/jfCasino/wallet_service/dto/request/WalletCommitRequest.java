package com.jfCasino.wallet_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Schema(
    name = "WalletCommitRequest",
    description = "Request to commit a previously reserved wallet transaction"
)
public class WalletCommitRequest {

    @Schema(
        description = "Identifier of the wallet reservation to commit",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    private UUID reservationID;

    @Schema(
        description = "Unique identifier of the user whose wallet is affected",
        example = "user-12345"
    )
    @NotBlank
    private String userID;

    @Schema(
        description = "Amount to commit from the reserved funds",
        example = "150",
        minimum = "0"
    )
    @Min(0)
    private int amount;
    public WalletCommitRequest() {};

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
