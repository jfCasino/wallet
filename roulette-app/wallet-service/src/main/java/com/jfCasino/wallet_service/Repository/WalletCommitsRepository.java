package com.jfCasino.wallet_service.Repository;

import com.jfCasino.wallet_service.Enitities.WalletCommit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface WalletCommitsRepository extends JpaRepository<WalletCommit, UUID> {
    
}
