package org.example.bankingsystem.service;

import org.example.bankingsystem.domain.Account;
import org.example.bankingsystem.repo.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accounts;

    public AccountService(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public Account createAccount(Account account) {
        accounts.findByIban(account.getIban()).ifPresent(a -> {
            throw new IllegalArgumentException("Account already exists: " +a.getIban());
        });
        return accounts.save(account);
    }

    public Optional<Account> getById(Long id) {
        return accounts.findById(id);
    }

    public Account getIban(String iban) {
        return accounts.findByIban(iban).orElseThrow(() -> new NoSuchElementException("Account not found: " +iban));
    }

    public List<Account> list() {return accounts.findAll(); }
}
