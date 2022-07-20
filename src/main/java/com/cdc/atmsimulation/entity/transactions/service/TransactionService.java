package com.cdc.atmsimulation.entity.transactions.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cdc.atmsimulation.entity.transactions.domain.Transaction;
import com.cdc.atmsimulation.entity.transactions.domain.Transaction.Type;
import com.cdc.atmsimulation.entity.users.domain.Account;
import com.cdc.atmsimulation.entity.users.service.AccountService;
import com.cdc.atmsimulation.interfaces.IRepository;

@Service
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

    public List<Transaction> getAllById(String accountNumber, int limit) {
        Optional<List<Transaction>> result = repository.getAllById(accountNumber);

        return result.get()
                .stream()
                .limit(limit)
                .sorted((x, y) -> x.getDate().compareTo(y.getDate()))
                .collect(Collectors.toList());
    }

    public void addTransaction(Transaction transaction) {
        Account account = accountService.getById(transaction.getAccount());

        if (account == null) {
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

    @Transactional
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
