package com.sg.kata.bank.account.kata.service;

import com.sg.kata.bank.account.kata.domain.Transaction;

import java.util.List;

public interface AccountService {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    List<Transaction> getTransactions();
}
