package com.example.walletapi.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletOperationRequest {
    private UUID walletId;
    private String currency;
    private BigDecimal amount;

    public WalletOperationRequest() {}

    public WalletOperationRequest(UUID walletId, String currency, BigDecimal amount) {
        this.walletId = walletId;
        this.currency = currency;
        this.amount = amount;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
