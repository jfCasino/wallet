package com.jfCasino.wallet_service.dto.request;

public class CreateWalletRequest {
    private String userID;

    public CreateWalletRequest() {}

    public CreateWalletRequest(String userID) {
        this.userID = userID;
    }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }
}
