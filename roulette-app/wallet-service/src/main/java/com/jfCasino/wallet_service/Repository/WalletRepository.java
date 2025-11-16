package com.jfCasino.wallet_service.Repository;

import com.jfCasino.wallet_service.Enitities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;


public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    Optional<Wallet> findByUserID(String externalUserId);

    boolean existsByUserID(String externalUserId);

}
