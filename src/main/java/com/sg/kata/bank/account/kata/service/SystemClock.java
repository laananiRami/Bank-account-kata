package com.sg.kata.bank.account.kata.service;

import java.time.LocalDateTime;

public class SystemClock implements Clock{
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
