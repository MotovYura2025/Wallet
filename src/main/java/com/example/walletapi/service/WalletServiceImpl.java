package com.example.walletapi.service;

import com.example.walletapi.entity.Wallet;
import com.example.walletapi.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    private Wallet getWalletOrThrow(UUID walletId) {
        return walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("Wallet not found: " + walletId));
    }

    @Override
    public void deposit(UUID walletId, BigDecimal amount) {
        Wallet wallet = getWalletOrThrow(walletId);
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
    }

    @Override
    public void withdraw(UUID walletId, BigDecimal amount) {
        Wallet wallet = getWalletOrThrow(walletId);
        if(wallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }
        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);
    }

    @Override
    public BigDecimal getBalance(UUID walletId) {
        Wallet wallet = getWalletOrThrow(walletId);
        return wallet.getBalance();
    }
}
