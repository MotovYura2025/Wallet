package com.example.walletapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Запрос на операцию с кошельком")
public class WalletOperationRequest {

    @NotNull
    @Schema(description = "UUID кошелька", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6", required = true)
    private UUID walletId;

    @NotNull
    @Schema(description = "Тип операции", allowableValues = {"DEPOSIT", "WITHDRAW"}, required = true)
    private OperationType operationType;

    @NotNull
    @Min(value = 1, message = "Сумма операции должна быть больше 0")
    @Schema(description = "Сумма операции", example = "1000", required = true)
    private BigDecimal amount;

    public enum OperationType {
        DEPOSIT,
        WITHDRAW
    }

    // Геттеры и сеттеры
    public UUID getWalletId() { return walletId; }
    public void setWalletId(UUID walletId) { this.walletId = walletId; }
    public OperationType getOperationType() { return operationType; }
    public void setOperationType(OperationType operationType) { this.operationType = operationType; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
