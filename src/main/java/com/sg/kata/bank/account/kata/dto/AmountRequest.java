package com.sg.kata.bank.account.kata.dto;

import jakarta.validation.constraints.Positive;

public class AmountRequest {
    @Positive(message = "Amount must be positive")
    private double amount;

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
