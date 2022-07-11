package com.cdc.atmsimulation.entity.transactions.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cdc.atmsimulation.entity.accounts.domain.Account;
import com.cdc.atmsimulation.entity.accounts.service.AccountService;
import com.cdc.atmsimulation.entity.transactions.domain.Transaction;
import com.cdc.atmsimulation.entity.transactions.domain.Transaction.Type;
import com.cdc.atmsimulation.interfaces.IRepository;

public class TransactionService {

    private IRepository<Transaction> repository;

    private AccountService accountService;

    public TransactionService(IRepository<Transaction> repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public List<Transaction> getAll() {
        return repository.getAll().get();
    }

    public void addTransaction(Transaction transaction) {
        Account account = accountService.getById(transaction.getAccount());

        if (account == null){
            System.out.println("failed: account not found");
            return;
        }

        int balance = account.getBalance();
        if (transaction.getTransactionType() == Type.RECEIVES)
            account.setBalance(balance + transaction.getAmount());
        else
            account.setBalance(balance - transaction.getAmount());

        accountService.editAccount(account);

        repository.add(transaction);
    }

    public void addTransaction(Account sender, Account receiver, int amount) {
        addTransaction(new Transaction(sender.getAccountNumber(),
                Type.TRANSFER,
                receiver.getAccountNumber(),
                amount,
                LocalDateTime.now()));
        addTransaction(new Transaction(receiver.getAccountNumber(),
                Type.RECEIVES,
                sender.getAccountNumber(),
                amount,
                LocalDateTime.now()));
    }

    public List<Transaction> getAllById(String accountNumber) {
        Optional<List<Transaction>> result = repository.getAllById(accountNumber);

        if (result.isEmpty()) {
            System.out.println("failed: no data found");
            return null;
        }

        return result.get().stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.toList());
    }
}
