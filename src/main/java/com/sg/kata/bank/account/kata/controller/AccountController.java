package com.sg.kata.bank.account.kata.controller;

import com.sg.kata.bank.account.kata.domain.Transaction;
import com.sg.kata.bank.account.kata.dto.AmountRequest;
import com.sg.kata.bank.account.kata.dto.TransactionDTO;
import com.sg.kata.bank.account.kata.mapper.TransactionMapper;
import com.sg.kata.bank.account.kata.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account/v1")
@Tag(name = "Account", description = "Bank Account API")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    @Operation(summary = "Make a deposit")
    public ResponseEntity<Void> deposit(@Valid @RequestBody AmountRequest request) {
        accountService.deposit(request.getAmount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    @Operation(summary = "Make a withdrawal")
    public ResponseEntity<Void> withdraw(@Valid @RequestBody AmountRequest request) {
        accountService.withdraw(request.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    @Operation(summary = "Get current balance")
    public ResponseEntity<Double> getBalance() {
        return ResponseEntity.ok(accountService.getBalance());
    }

    @GetMapping("/transactions")
    @Operation(summary = "Get transaction history")
    public ResponseEntity<List<TransactionDTO>> getTransactions() {
        List<TransactionDTO> transactions = accountService.getTransactions()
                .stream()
                .map(TransactionMapper::toTransactionDTO)
                .toList();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/statement")
    @Operation(summary = "Get formatted statement")
    public ResponseEntity<List<Transaction>> getStatement() {
        return ResponseEntity.ok(accountService.getTransactions());
    }
}

