package com.example.walletapi.service;

import com.example.walletapi.entity.Wallet;
import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {
    void deposit(UUID walletId, String currency, BigDecimal amount);
    void withdraw(UUID walletId, String currency, BigDecimal amount);
    BigDecimal getBalance(UUID walletId, String currency);
    Wallet getWallet(UUID walletId);
}
