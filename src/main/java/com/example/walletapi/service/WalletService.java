package com.example.walletapi.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {
    void deposit(UUID walletId, BigDecimal amount);
    void withdraw(UUID walletId, BigDecimal amount);
    BigDecimal getBalance(UUID walletId);
}
