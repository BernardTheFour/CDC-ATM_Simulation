package app.csv_access;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.domains.Transaction;
import app.domains.Transaction.Type;
import app.pattern.SingletonPath;
import app.pattern.SingletonUtils;

public class CSVTransaction implements IFileManipulation<Transaction> {

    private DataAccess dataAccess;

    public CSVTransaction(File file) {
        dataAccess = new DataAccess(file);
    }

    @Override
    public Optional<Transaction> getById(String id) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List<Transaction> getAllById(String id) {
        Optional<List<String>> result = dataAccess.getAllByid(id);

        if (result.isEmpty()) {
            System.out.printf("failed: account %s not found%n", id);
            return null;
        }

        List<Transaction> transactions = new ArrayList<>();

        result.get().forEach(data -> {
            List<String> toList = List.of(data.split(SingletonUtils.getCSVColumnDelimiter()));
            transactions.add(readLine(toList));
        });

        return transactions;
    }

    @Override
    public void edit(Transaction data) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void save() {
        String list = "";

        for (Transaction transaction : Transaction.get()) {
            list += writeLine(transaction);
        }

        SingletonPath.setTransactions(
                dataAccess.save(SingletonPath.getTransactions(), list));
    }

    @Override
    public Optional<List<Transaction>> getAll() {
        Optional<List<String>> result = dataAccess.getAll();
        List<Transaction> data = new ArrayList<>();

        if (result.isEmpty()) {
            return Optional.empty();
        }

        result.get().forEach(member -> {
            List<String> line = List.of(member.split(SingletonUtils.getCSVColumnDelimiter()));
            data.add(readLine(line));
        });

        return Optional.of(data);
    }

    @Override
    public void add(Transaction data) {
        List<Transaction> transactions = Transaction.get();
        transactions.add(data);
        Transaction.set(transactions);

        SingletonPath.setTransactions(
                dataAccess.add(SingletonPath.getTransactions(), writeLine(data)));
    }

    private String writeLine(Transaction transaction) {
        String list = "";

        list += transaction.getAccountNumber() + SingletonUtils.getCSVColumnDelimiter();
        list += transaction.getTransactionType().name() + SingletonUtils.getCSVColumnDelimiter();
        list += transaction.getAssociate() + SingletonUtils.getCSVColumnDelimiter();
        list += transaction.getAmount() + SingletonUtils.getCSVColumnDelimiter();
        list += transaction.getDate() + SingletonUtils.getCSVRowDelimiter();

        return list;
    }

    private Transaction readLine(List<String> data) {
        Transaction transaction = new Transaction(
                data.get(0),
                Type.valueOf(data.get(1)),
                data.get(2),
                Integer.valueOf(data.get(3)),
                LocalDateTime.parse(data.get(4)));

        return transaction;
    }
}
