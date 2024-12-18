package com.sg.kata.bank.account.kata.domain;

import java.time.LocalDateTime;

public class Transaction {
    private final LocalDateTime date;
    private final OperationType type;
    private final double amount;
    private final double balance;

    public Transaction(LocalDateTime date, OperationType type, double amount, double balance) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public double getAmount() {
        return amount;
    }

    public OperationType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
