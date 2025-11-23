package com.example.walletapi.controller;

import com.example.walletapi.entity.Transaction;
import com.example.walletapi.repository.TransactionRepository;
import com.example.walletapi.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final TransactionRepository transactionRepository;
    private final ReportService reportService;

    public ReportController(TransactionRepository transactionRepository, ReportService reportService) {
        this.transactionRepository = transactionRepository;
        this.reportService = reportService;
    }

    @GetMapping("/transactions/csv")
    public ResponseEntity<byte[]> getTransactionsCsv() {
        List<Transaction> transactions = transactionRepository.findAll();
        ByteArrayInputStream stream = reportService.generateCsvReport(transactions);
        byte[] bytes = stream.readAllBytes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(bytes);
    }
}
