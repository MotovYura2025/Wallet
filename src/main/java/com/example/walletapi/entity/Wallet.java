package com.example.walletapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity public class Wallet {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID walletId;

    @ElementCollection @CollectionTable(name = "wallet_balances", joinColumns = @JoinColumn(name = "wallet_id"))
    @MapKeyColumn(name = "currency")
    @Column(name = "balance")
    private Map<String, BigDecimal> balances;

    public Wallet() {
        this.balances = new HashMap<>();
    }

    public Wallet(UUID walletId, Map<String, BigDecimal> balances) {
        this.walletId = walletId;
        this.balances = balances != null ? balances : new HashMap<>();
    }

    public Wallet(UUID walletId, BigDecimal balance, String currency) {
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public Map<String, BigDecimal> getBalances() {
        return balances;
    }

    public void setBalances(Map<String, BigDecimal> balances) {
        this.balances = balances != null ? balances : new HashMap<>();
    }

    public String getCurrency() {
        return "";
    }

    public BigDecimal getBalance() {
        return null;
    }
}
