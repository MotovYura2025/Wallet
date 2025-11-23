package com.example.walletapi.service;

import com.example.walletapi.entity.Transaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    public ByteArrayInputStream generateCsvReport(List<Transaction> transactions) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("ID", "Wallet ID", "Amount", "Date", "Type", "Status");
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {

            for (Transaction t : transactions) {
                csvPrinter.printRecord(t.getId(), t.getWalletId(), t.getAmount(),
                        t.getDate(), t.getOperationType(), t.getStatus());
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании CSV отчета", e);
        }
    }
}
