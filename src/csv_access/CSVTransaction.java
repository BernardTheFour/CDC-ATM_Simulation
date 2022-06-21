package csv_access;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domains.Transaction;
import domains.Transaction.Type;
import pattern.SingletonData;
import pattern.SingletonPath;
import pattern.SingletonUtils;

public class CSVTransaction implements IFileManipulation<Transaction> {

    private DataAccess dataAccess;

    public CSVTransaction(File file) {
        dataAccess = new DataAccess(file);
    }

    @Override
    public Optional<Transaction> getById(String id) {
        Optional<String> result = dataAccess.getById(id);

        if (result.isEmpty()) {
            System.out.printf("failed: account %s not found%n", id);
            return Optional.empty();
        }

        List<String> data = List.of(result.get().split(SingletonUtils.getCSVColumnDelimiter()));

        return Optional.of(read(data));
    }

    @Override
    public void edit(Transaction data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not implemented yet");

    }

    @Override
    public void save() {
        String list = "";

        for (Transaction transaction : SingletonData.getTransactions()) {
            list += SingletonUtils.getCSVRowDelimiter();
            list += transaction.getAccountNumber() + SingletonUtils.getCSVColumnDelimiter();
            list += transaction.getTransactionType() + SingletonUtils.getCSVColumnDelimiter();
            list += transaction.getTransferTo() + SingletonUtils.getCSVColumnDelimiter();
            list += transaction.getAmount() + SingletonUtils.getCSVColumnDelimiter();
            list += transaction.getDate();
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
            data.add(read(line));
        });

        return Optional.of(data);
    }

    @Override
    public void add(Transaction data) {
        // TODO Auto-generated method stub

    }

    private Transaction read(List<String> data) {
        Transaction transaction = new Transaction(
                data.get(0),
                Type.valueOf(data.get(1)),
                data.get(2),
                Integer.valueOf(data.get(3)),
                LocalDateTime.parse(data.get(4)));

        return transaction;
    }
}
