package com.sg.kata.bank.account.kata.dto;

import com.sg.kata.bank.account.kata.domain.OperationType;

import java.time.LocalDateTime;

public class TransactionDTO {

    private LocalDateTime date;
    private OperationType type;
    private double amount;
    private double balance;

    public TransactionDTO() {}

    public TransactionDTO(LocalDateTime date, OperationType type, double amount, double balance) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
