package com.jfCasino.wallet_service.Repository;

import com.jfCasino.wallet_service.Enitities.WalletReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletReservationRepository extends JpaRepository<WalletReservation, UUID> {
    
}
