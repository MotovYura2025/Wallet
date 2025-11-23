package com.example.walletapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.walletapi.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
