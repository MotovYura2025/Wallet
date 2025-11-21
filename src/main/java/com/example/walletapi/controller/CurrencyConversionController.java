package com.example.walletapi.сontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.walletapi.service.CurrencyConversionService;

@RestController
@RequestMapping("/api/convert")
public class CurrencyConversionController {

    private final CurrencyConversionService conversionService;

    public CurrencyConversionController(CurrencyConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping
    public ResponseEntity<Double> convert(@RequestParam String from,
                                          @RequestParam String to,
                                          @RequestParam double amount) {
        double result = conversionService.convertCryptoToFiat(from, to, amount);
        return ResponseEntity.ok(result);
    }
}
