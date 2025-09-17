package org.example.bankingsystem.service;

import org.example.bankingsystem.domain.Account;
import org.example.bankingsystem.domain.Transaction;
import org.example.bankingsystem.repo.AccountRepository;
import org.example.bankingsystem.repo.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferService {
    private final AccountRepository accounts;
    private final TransactionRepository transactions;

    public TransferService(AccountRepository accounts, TransactionRepository transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }

    @Transactional
    public Transaction transfer(String fromIban, String toIban, BigDecimal amount){
        if(fromIban.equals(toIban)) {
            throw new IllegalArgumentException("From = To");
        }

        Account from = accounts.findByIban(fromIban).orElseThrow();
        Account to = accounts.findByIban(toIban).orElseThrow();

        if (from.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("insufficient balance");
        }

        if (amount == null || amount.signum() <= 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));
        accounts.save(from);
        accounts.save(to);

        return transactions.save(new Transaction(from, to, amount));
    }
}
