package com.example.walletapi;

import com.example.walletapi.entity.Wallet;
import com.example.walletapi.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest(classes = WalletApiApplication.class)
class WalletApplicationIntegrationTest {

    @Autowired private WalletRepository walletRepository;

    @Test void testWalletCreation() {
        UUID walletId = UUID.randomUUID();
        BigDecimal balance = BigDecimal.valueOf(100);
        String currency = "RUB";
        Wallet wallet = new Wallet(walletId, balance, currency);
        walletRepository.save(wallet);
        // Добавьте дополнительные проверки, например:
        // assertThat(walletRepository.findById(walletId)).isPresent();
    }
}
