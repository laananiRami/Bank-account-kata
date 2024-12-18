package com.sg.kata.bank.account.kata.mapper;

import com.sg.kata.bank.account.kata.domain.Transaction;
import com.sg.kata.bank.account.kata.dto.TransactionDTO;

public class TransactionMapper {

    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getDate(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getBalance()
        );
    }
}
