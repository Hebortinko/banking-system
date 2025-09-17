package org.example.bankingsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.bankingsystem.domain.Account;

import java.util.Objects;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findbyIban(String iban);
}
