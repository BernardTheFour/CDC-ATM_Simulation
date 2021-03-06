package com.cdc.atmsimulation.entity.transactions.repository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.cdc.atmsimulation.entity.transactions.domain.Transaction;
import com.cdc.atmsimulation.entity.transactions.domain.Transaction.Type;
import com.cdc.atmsimulation.interfaces.IRepository;
import com.cdc.atmsimulation.pattern.singletons.SingletonFile;
import com.cdc.atmsimulation.pattern.singletons.SingletonUtils;
import com.cdc.atmsimulation.util.FileManager;

@Repository
public class FileRepoTransaction implements IRepository<Transaction> {

    private FileManager fileManager;

    public FileRepoTransaction() {
        fileManager = new FileManager(SingletonFile.getTransactions());
    }

    @Override
    public Optional<Transaction> getById(String id) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public Optional<List<Transaction>> getAllById(String id) {
        try (Stream<String> result = fileManager.getAll()) {

            return Optional.of(
                    result.filter(i -> i.split(SingletonUtils.getCSVColumnDelimiter())[0].equals(id))
                            .map(i -> {
                                List<String> toList = List.of(i.split(SingletonUtils.getCSVColumnDelimiter()));
                                return readLine(toList);
                            }).collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return Optional.empty();
        }
    }

    @Override
    public void edit(Transaction data) {
        throw new UnsupportedOperationException("Not implemented yet");
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
    public void add(Transaction data) {
        fileManager.add(writeLine(data));
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
        return new Transaction(
            data.get(0),
            Type.valueOf(data.get(1)),
            data.get(2),
            Integer.valueOf(data.get(3)),
            LocalDateTime.parse(data.get(4)));
    }
}
