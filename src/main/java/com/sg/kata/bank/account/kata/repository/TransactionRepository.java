package com.sg.kata.bank.account.kata.repository;

import com.sg.kata.bank.account.kata.domain.Transaction;

import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);
    List<Transaction> findAll();
}
