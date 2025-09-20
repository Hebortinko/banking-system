package org.example.bankingsystem.api;

import org.example.bankingsystem.domain.Account;
import org.example.bankingsystem.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //POST /api/accounts/ creates and account
    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account request) {
        Account saved = accountService.createAccount(request);
        return ResponseEntity
                .created(URI.create("/api/accounts" + saved.getId()))
                .body(saved);
    }

    //GET /api/account/{id} detail
    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id) {
        Optional<Account> acc = accountService.getById(id);
        return acc.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //GET /api/accounts list
    @GetMapping
    public List<Account> list() {
        return accountService.list();
    }
}
