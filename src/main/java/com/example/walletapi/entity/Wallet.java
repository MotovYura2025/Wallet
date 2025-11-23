package com.example.walletapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "wallet_balances", joinColumns = @JoinColumn(name = "wallet_id"))
    @MapKeyColumn(name = "currency")
    @Column(name = "balance")
    private Map<String, BigDecimal> balances;

    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;

    protected Wallet() {
        // JPA constructor
    }

    public Wallet(UUID id, Map<String, BigDecimal> balances, String ownerEmail) {
        this.id = id;
        this.balances = balances;
        this.ownerEmail = ownerEmail;
    }

    public UUID getId() {
        return id;
    }

    public Map<String, BigDecimal> getBalances() {
        return balances;
    }

    public void setBalances(Map<String, BigDecimal> balances) {
        this.balances = balances;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
