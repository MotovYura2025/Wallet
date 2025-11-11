package com.example.walletapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Ответ с текущим балансом кошелька")
public class WalletBalanceResponse {

    @Schema(description = "UUID кошелька", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID walletId;

    @Schema(description = "Текущий баланс", example = "1500")
    private BigDecimal balance;

    public WalletBalanceResponse(UUID walletId, BigDecimal balance) {
        this.walletId = walletId;
        this.balance = balance;
    }

    // Геттеры
    public UUID getWalletId() { return walletId; }
    public BigDecimal getBalance() { return balance; }
}
