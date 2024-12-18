package com.sg.kata.bank.account.kata.service;

import com.sg.kata.bank.account.kata.domain.OperationType;
import com.sg.kata.bank.account.kata.domain.Transaction;
import com.sg.kata.bank.account.kata.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import(AccountServiceImpl.class)
class AccountServiceImplTest {
    @Autowired
    private AccountService accountService;

    @MockitoBean
    private TransactionRepository transactionRepository;

    @MockitoBean
    private Clock clock;

    private final LocalDateTime fixedDateTime = LocalDateTime.of(2024, 1, 1, 10, 0);

    @Test
    void deposit_shouldIncreaseBalance_whenAmountIsPositive() {
        // Given
        when(clock.now()).thenReturn(fixedDateTime);
        double initialBalance = accountService.getBalance();
        double depositAmount = 100.0;

        // When
        accountService.deposit(depositAmount);

        // Then
        assertEquals(initialBalance + depositAmount, accountService.getBalance());

        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository).save(transactionCaptor.capture());

        Transaction savedTransaction = transactionCaptor.getValue();
        assertEquals(OperationType.DEPOSIT, savedTransaction.getType());
        assertEquals(depositAmount, savedTransaction.getAmount());
        assertEquals(fixedDateTime, savedTransaction.getDate());
    }

    @Test
    void deposit_shouldThrowException_whenAmountIsZero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> accountService.deposit(0.0)
        );
        assertEquals("Amount must be positive", exception.getMessage());
        verify(transactionRepository, never()).save(any());
    }

    @Test
    void deposit_shouldThrowException_whenAmountIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> accountService.deposit(-100.0)
        );
        assertEquals("Amount must be positive", exception.getMessage());
        verify(transactionRepository, never()).save(any());
    }

    @Test
    void withdraw_shouldDecreaseBalance_whenSufficientFunds() {
        // Given
        when(clock.now()).thenReturn(fixedDateTime);
        accountService.deposit(200.0); // Setup initial balance
        double withdrawAmount = 50.0;
        double expectedBalance = 150.0;

        // When
        accountService.withdraw(withdrawAmount);

        // Then

        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository, times(2)).save(transactionCaptor.capture()); // Once for deposit, once for withdrawal

        Transaction withdrawalTransaction = transactionCaptor.getValue();
        assertEquals(OperationType.WITHDRAWAL, withdrawalTransaction.getType());
        assertEquals(withdrawAmount, withdrawalTransaction.getAmount());
        assertEquals(fixedDateTime, withdrawalTransaction.getDate());
    }

    @Test
    void withdraw_shouldThrowException_whenInsufficientFunds() {
        // Given
        accountService.deposit(50.0);

        // When & Then
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> accountService.withdraw(100.0)
        );
        assertEquals("Insufficient funds", exception.getMessage());
        assertEquals(50.0, accountService.getBalance()); // Balance should remain unchanged
    }
}