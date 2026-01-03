package com.jfCasino.wallet_service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@Schema(
    name = "CreateWalletRequest",
    description = "Request payload for creating a new wallet for a user"
)
public class CreateWalletRequest {

    @Schema(
        description = "Unique identifier of the user for whom the wallet will be created",
        example = "user-12345"
    )
    @NotBlank
    private String userID;

    public CreateWalletRequest() {}

    public CreateWalletRequest(String userID) {
        this.userID = userID;
    }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }
}
