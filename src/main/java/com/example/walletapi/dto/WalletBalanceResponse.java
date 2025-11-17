package com.example.walletapi.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletBalanceResponse {
    private UUID walletId;
    private String currency;
    private BigDecimal balance;

    public WalletBalanceResponse() {}

    public WalletBalanceResponse(UUID walletId, String currency, BigDecimal balance) {
        this.walletId = walletId;
        this.currency = currency;
        this.balance = balance;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
