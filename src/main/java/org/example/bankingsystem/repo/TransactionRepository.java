package org.example.bankingsystem.repo;

import org.example.bankingsystem.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccount_IdOrToAccount_IdOrderByCreatedAtDesc(Long accountId, Long toAccountId);
}
