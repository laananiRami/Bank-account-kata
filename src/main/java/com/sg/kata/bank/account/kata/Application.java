package com.sg.kata.bank.account.kata;

import com.sg.kata.bank.account.kata.repository.InMemoryTransactionRepository;
import com.sg.kata.bank.account.kata.repository.TransactionRepository;
import com.sg.kata.bank.account.kata.service.AccountService;
import com.sg.kata.bank.account.kata.service.AccountServiceImpl;
import com.sg.kata.bank.account.kata.service.Clock;
import com.sg.kata.bank.account.kata.service.SystemClock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public Clock clock() {
		return new SystemClock();
	}

	@Bean
	public TransactionRepository transactionRepository() {
		return new InMemoryTransactionRepository();
	}

	@Bean
	public AccountService accountService(TransactionRepository repository, Clock clock) {
		return new AccountServiceImpl(repository, clock);
	}

}
