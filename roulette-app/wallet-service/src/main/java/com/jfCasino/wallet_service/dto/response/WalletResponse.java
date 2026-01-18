package com.jfCasino.wallet_service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;
import java.math.BigDecimal;

@Schema(
    name = "WalletResponse",
    description = "Response representing the current state of a wallet"
)
public class WalletResponse {

    @Schema(
        description = "Unique identifier of the wallet",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    private UUID walletID;

    @Schema(
        description = "Unique identifier of the user who owns the wallet",
        example = "user-12345"
    )
    private String userID;

    @Schema(
        description = "Current balance of the wallet",
        example = "850"
    )
    private BigDecimal balance;
    
    public WalletResponse() {};

    public WalletResponse(UUID walletID, String userID, BigDecimal balance) {
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

    public BigDecimal getBalance() {
        return balance;
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
