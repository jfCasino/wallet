package com.jfCasino.wallet_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Schema(
    name = "WalletReserveRequest",
    description = "Request to reserve funds in a wallet for a transaction"
)
public class WalletReserveRequest {

    @Schema(
        description = "Unique identifier of the user whose funds are to be reserved",
        example = "user-12345"
    )
    @NotBlank
    private String userID;

    @Schema(
        description = "Amount of funds to reserve",
        example = "150",
        minimum = "1"
    )
    @Min(1)
    private BigDecimal amount;
    public WalletReserveRequest() {};
    //getters and setters
    
    public BigDecimal getAmount() {
        return amount;
    }

    public String getUserID() {
        return userID;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
