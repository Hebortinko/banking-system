package org.example.bankingsystem.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 34)
    private String iban;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balance = BigDecimal.ZERO;

    public Account() {}

    public Account(String iban, String currency, BigDecimal balance) {
        this.iban = iban;
        this.currency = currency;
        this.balance = BigDecimal.ZERO;
    }

    // getters & setters
    //Id
    public Long getId() {
        return id;
    }

    //Iban
    public String getIban() {
        return iban;
    }
    void setIban(String iban) {
        this.iban = iban;
    }

    //Currency
    public String getCurrency() {
        return currency;
    }
    void setCurrency(String currency) {
        this.currency = currency;
    }

    //Balance
    public BigDecimal getBalance() {
        return balance;
    }
    void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
