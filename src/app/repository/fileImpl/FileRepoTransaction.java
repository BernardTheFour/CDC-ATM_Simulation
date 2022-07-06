package app.repository.fileImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import app.domains.Transaction;
import app.domains.Transaction.Type;
import app.pattern.SingletonPath;
import app.pattern.SingletonUtils;
import app.repository.IRepository;
import app.util.FileManager;

public class FileRepoTransaction implements IRepository<Transaction> {

    private FileManager fileManager;

    public FileRepoTransaction(File file) {
        fileManager = new FileManager(file);
    }

    @Override
    public Optional<Transaction> getById(String id) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List<Transaction> getAllById(String id) {
        Optional<List<String>> result = fileManager.getAllByid(id);

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
    public Stream<Transaction> edit(Transaction data) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void save(Stream<Transaction> stream) {
        StringBuilder sb = new StringBuilder();

        stream.forEach(i -> sb.append(writeLine(i)));

        SingletonPath.setTransactions(
                fileManager.save(SingletonPath.getTransactions(), sb.toString()));
    }

    @Override
    public Optional<List<Transaction>> getAll() {
        try (Stream<String> result = fileManager.getAll()) {

            return Optional.of(result
                    .map(i -> {
                        List<String> line = List.of(i.split(SingletonUtils.getCSVColumnDelimiter()));
                        return readLine(line);
                    }).collect(Collectors.toList()));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return Optional.empty();
        }
    }

    @Override
    public Stream<Transaction> add(Transaction data) {
        List<Transaction> transactions = Transaction.getData();
        transactions.add(data);
        Transaction.setData(transactions);

        SingletonPath.setTransactions(
                fileManager.add(SingletonPath.getTransactions(), writeLine(data)));
        return null;
    }

    private String writeLine(Transaction transaction) {
        String list = SingletonUtils.getCSVRowDelimiter();

        list += transaction.getAccount() + SingletonUtils.getCSVColumnDelimiter();
        list += transaction.getTransactionType().name() + SingletonUtils.getCSVColumnDelimiter();
        list += transaction.getAssociate() + SingletonUtils.getCSVColumnDelimiter();
        list += transaction.getAmount() + SingletonUtils.getCSVColumnDelimiter();
        list += transaction.getDate() + ";";

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
