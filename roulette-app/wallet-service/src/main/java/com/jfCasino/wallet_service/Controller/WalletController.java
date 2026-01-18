package com.jfCasino.wallet_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfCasino.wallet_service.Enitities.Wallet;
import com.jfCasino.wallet_service.Enitities.WalletCommit;
import com.jfCasino.wallet_service.Enitities.WalletReservation;
import com.jfCasino.wallet_service.Service.WalletService;
import com.jfCasino.wallet_service.Service.CurrencyService;
import com.jfCasino.wallet_service.dto.request.CreateWalletRequest;
import com.jfCasino.wallet_service.dto.request.WalletCommitRequest;
import com.jfCasino.wallet_service.dto.request.WalletReserveRequest;
import com.jfCasino.wallet_service.dto.response.WalletCommitResponse;
import com.jfCasino.wallet_service.dto.response.WalletReserveResponse;
import com.jfCasino.wallet_service.dto.response.WalletResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import java.math.BigDecimal;

import java.util.stream.Collectors;
import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Wallet Service", description = "Endpoints for managing user wallets")
public class WalletController {

    private final WalletService walletService;
    private final CurrencyService currencyService;

    //Constructor injection
    public WalletController(WalletService walletService, CurrencyService currencyService) {
        this.walletService = walletService;
        this.currencyService = currencyService;
    }

    @PostMapping("/wallets/create")
    @Operation(summary = "Create a new wallet for a user",
               description = "Creates a new wallet associated with the given user ID and returns the wallet UUID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Wallet created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user ID provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public UUID createWallet(@RequestBody CreateWalletRequest request) {
        return walletService.createWallet(request.getUserID());
    }

    @GetMapping("/wallets/{userID}")
    @Operation(summary = "Retrieve a user's wallet",
               description = "Returns the wallet details for the given user ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Wallet retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Wallet not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<WalletResponse> getWallet( @Parameter(description = "User ID of the wallet to retrieve", example = "user-12345")
            @PathVariable("userID") String userID) {
        Wallet wallet = walletService.getBalance(userID);
        WalletResponse response = new WalletResponse(wallet.getWalletID() ,wallet.getUserID(), wallet.getBalance());
 

        return ResponseEntity.ok(response);
    }

    @GetMapping("/wallets/{userID}/yen")
    @Operation(
        summary = "Retrieve a user's wallet balance in JPY",
        description = "Returns the wallet details for the given user ID with the balance converted from EUR to JPY"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Wallet retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Wallet not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<WalletResponse> getWalletYen(
            @Parameter(
                description = "User ID of the wallet to retrieve",
                example = "user-12345"
            )
            @PathVariable("userID") String userID
    ) {

        Wallet wallet = walletService.getBalance(userID);

        BigDecimal balanceInYen = currencyService.convertEurToJpy(
                wallet.getBalance()
        );

        WalletResponse response = new WalletResponse(
                wallet.getWalletID(),
                wallet.getUserID(),
                balanceInYen
        );

        return ResponseEntity.ok(response);
    }

    //JF do te metode bo dostopal le stats service
    @GetMapping("/wallets")
    @Operation(summary = "Retrieve a list of wallets",
               description = "Returns a paginated list of wallets, ordered by balance or user ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Wallets retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid query parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<WalletResponse>> getAllWallets(
            @Parameter(description = "Sorting order of wallets, 'asc' or 'desc'", example = "asc")
            @RequestParam(name = "order", defaultValue = "asc") String order,
            @Parameter(description = "Maximum number of wallets to return", example = "10")
            @RequestParam(name = "limit", defaultValue = "10") int limit) {

        //TODO change it so you do not return a List of Etities, but a list od DTOs?
        List<Wallet> wallets = walletService.getTopWallets(order, limit).getContent();
        List<WalletResponse> response = wallets.stream()
            .map(wallet -> new WalletResponse(
            wallet.getWalletID(),
            wallet.getUserID(),
            wallet.getBalance()
            )).collect(Collectors.toList());


        return ResponseEntity.ok(response);
    }

    //JF do te metode bo dostopal le rulette service
    @Operation(summary = "Reserve funds in a wallet",
               description = "Creates a reservation for a specific amount in the user's wallet")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Funds reserved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/wallets/reserve")
    public ResponseEntity<WalletReserveResponse> postReserve(@RequestBody WalletReserveRequest request) {
        WalletReservation reservation = walletService.createReservation(request.getUserID(), request.getAmount());
        WalletReserveResponse response = new WalletReserveResponse(
            reservation.getReservationID(),
            reservation.getUserID(),
            reservation.getAmount(),
            reservation.getStatus(),
            reservation.getCreatedAt(),
            reservation.getExpiresAt()
        );


        return ResponseEntity.ok(response);
    }

    //JF commit vrne rezervirana sredstva + dobicek
    @PostMapping("/wallets/commit")
    @Operation(summary = "Commit a reserved wallet transaction",
               description = "Commits a previously reserved amount and updates the user's wallet balance")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction committed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request or reservation"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<WalletCommitResponse> postCommit(@RequestBody WalletCommitRequest request) {
        WalletCommit commit = walletService.commit(
            request.getReservationID(),
            request.getUserID(),
            request.getAmount()
        );
        
        WalletCommitResponse response = new WalletCommitResponse(
            commit.getCommitID(),
            commit.getReservationID(),
            commit.getUserID(),
            commit.getAmount(),
            commit.getNewBalance(),
            commit.getCreatedAt()
        );
        
        
        return ResponseEntity.ok(response);
    }

}