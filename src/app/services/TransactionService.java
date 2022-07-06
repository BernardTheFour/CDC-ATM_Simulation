package app.services;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import app.domains.Account;
import app.domains.Transaction;
import app.domains.Transaction.Type;
import app.repository.IRepository;
import app.repository.fileImpl.FileRepoTransaction;

public class TransactionService {

    private static TransactionService instance;

    private static IRepository<Transaction> fileRepoTransaction;

    public TransactionService() {
    }

    public TransactionService(File file) {
        if (instance == null) {
            instance = new TransactionService();
            fileRepoTransaction = new FileRepoTransaction(file);
        }
    }

    public static List<Transaction> getAll() {
        return fileRepoTransaction.getAll().get();
    }

    public static void addTransaction(Transaction transaction) {
        Account account = AccountService.getById(transaction.getAccount());

        int balance = account.getBalance();
        if (transaction.getTransactionType() == Type.RECEIVE)
            account.setBalance(balance + transaction.getAmount());
        else
            account.setBalance(balance - transaction.getAmount());

        AccountService.editAccount(account);

        fileRepoTransaction.add(transaction);
    }

    public static void addTransaction(Account sender, Account receiver, int amount) {
        addTransaction(new Transaction(sender.getAccountNumber(),
                Type.TRANSFER,
                receiver.getAccountNumber(),
                amount,
                LocalDateTime.now()));
        addTransaction(new Transaction(receiver.getAccountNumber(),
                Type.RECEIVE,
                sender.getAccountNumber(),
                amount,
                LocalDateTime.now()));
    }

    public static List<Transaction> getAllById(String accountNumber) {
        List<Transaction> result = fileRepoTransaction.getAllById(accountNumber);

        return result.stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.toList());
    }
}
