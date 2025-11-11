package com.example.walletapi.Controller;

import com.example.walletapi.dto.WalletBalanceResponse;
import com.example.walletapi.dto.WalletOperationRequest;
import com.example.walletapi.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @Operation(summary = "Выполнить операцию пополнения или снятия с кошелька")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Операция выполнена успешно, возвращён текущий баланс"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос: неверные данные"),
            @ApiResponse(responseCode = "409", description = "Конфликт при операции: недостаточно средств"),
            @ApiResponse(responseCode = "404", description = "Кошелёк не найден")
    })
    @PostMapping("/wallet")
    public ResponseEntity<WalletBalanceResponse> processOperation(@Valid @RequestBody WalletOperationRequest request) {
        try {
            switch (request.getOperationType()) {
                case DEPOSIT:
                    walletService.deposit(request.getWalletId(), request.getAmount());
                    break;
                case WITHDRAW:
                    walletService.withdraw(request.getWalletId(), request.getAmount());
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid operationType");
            }
            BigDecimal balance = walletService.getBalance(request.getWalletId());
            return ResponseEntity.ok(new WalletBalanceResponse(request.getWalletId(), balance));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Operation(summary = "Получить баланс по UUID кошелька")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Баланс успешно получен"),
            @ApiResponse(responseCode = "404", description = "Кошелёк не найден")
    })
    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<WalletBalanceResponse> getBalance(@PathVariable UUID walletId) {
        try {
            BigDecimal balance = walletService.getBalance(walletId);
            return ResponseEntity.ok(new WalletBalanceResponse(walletId, balance));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wallet not found");
        }
    }
}
