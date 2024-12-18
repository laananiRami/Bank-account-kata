package com.sg.kata.bank.account.kata.repository;

import com.sg.kata.bank.account.kata.domain.Transaction;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository{
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }
}
