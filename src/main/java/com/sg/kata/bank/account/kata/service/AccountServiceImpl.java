package com.sg.kata.bank.account.kata.service;

import com.sg.kata.bank.account.kata.domain.OperationType;
import com.sg.kata.bank.account.kata.domain.Transaction;
import com.sg.kata.bank.account.kata.repository.TransactionRepository;

import java.util.List;

public class AccountServiceImpl implements AccountService{
    private double balance;
    private final TransactionRepository transactionRepository;
    private final Clock clock;

    public AccountServiceImpl(TransactionRepository transactionRepository, Clock clock) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
        this.balance = 0.0;
    }
    @Override
    public void deposit(double amount) {
        validateAmount(amount);
        balance += amount;
        recordTransaction(OperationType.DEPOSIT, amount);
    }

    @Override
    public void withdraw(double amount) {
        validateAmount(amount);
        validateSufficientFunds(amount);
        balance -= amount;
        recordTransaction(OperationType.WITHDRAWAL, amount);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public List<Transaction> getTransactions() {
        return List.of();
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    private void recordTransaction(OperationType type, double amount) {
        Transaction transaction = new Transaction(clock.now(), type, amount, balance);
        transactionRepository.save(transaction);
    }

    private void validateSufficientFunds(double amount) {
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
    }
}
