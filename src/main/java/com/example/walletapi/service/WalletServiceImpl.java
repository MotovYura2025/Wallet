package com.example.walletapi.service;

import com.example.walletapi.entity.Wallet;
import com.example.walletapi.exception.WalletNotFoundException;
import com.example.walletapi.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.Map;

@Service public class WalletServiceImpl implements WalletService {

    @Autowired private WalletRepository walletRepository;

    @Override public void deposit(UUID walletId, String currency, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
        Map<String, BigDecimal> balances = wallet.getBalances();
        balances.put(currency, balances.getOrDefault(currency, BigDecimal.ZERO).add(amount));
        wallet.setBalances(balances);
        walletRepository.save(wallet);
    }

    @Override public void withdraw(UUID walletId, String currency, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
        Map<String, BigDecimal> balances = wallet.getBalances();
        BigDecimal balance = balances.getOrDefault(currency, BigDecimal.ZERO);
        if (balance.compareTo(amount) <0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balances.put(currency, balance.subtract(amount));
        wallet.setBalances(balances);
        walletRepository.save(wallet);
    }

    @Override public BigDecimal getBalance(UUID walletId, String currency) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
        return wallet.getBalances().getOrDefault(currency, BigDecimal.ZERO);
    }

    @Override public Wallet getWallet(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));
    }
}
