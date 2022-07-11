package app.entity.accounts.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import app.entity.accounts.domain.Account;
import app.interfaces.IRepository;
import app.pattern.singletons.SingletonUtils;
import app.util.FileManager;

public class FileRepoAccount implements IRepository<Account> {

    private FileManager fileManager;

    public FileRepoAccount(File file) {
        fileManager = new FileManager(file);
    }

    @Override
    public Optional<Account> getById(String id) {
        Optional<String> result = fileManager.getById(id);

        if (result.isEmpty()) {
            System.out.printf("failed: account %s not found%n", id);
            return Optional.empty();
        }

        List<String> data = List.of(result.get().split(SingletonUtils.getCSVColumnDelimiter()));

        return Optional.of(readLine(data));
    }

    @Override
    public Optional<List<Account>> getAll() {
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
    public void edit(Account data) {
        StringBuilder sb = new StringBuilder();

        Optional<List<Account>> result = getAll();

        if (result.isPresent()) {
            result.get().stream()
                    .map(x -> {
                        if (x.getAccountNumber().equals(data.getAccountNumber())) {
                            x.setBalance(data.getBalance());
                        }
                        return x;
                    })
                    .forEach(x -> sb.append(writeLine(x)));

            fileManager.edit(sb.toString());
            return;
        }

        throw new NoSuchElementException();
    }

    @Override
    public void add(Account data) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Optional<List<Account>> getAllById(String id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private Account readLine(List<String> data) {
        return new Account(
                data.get(0),
                data.get(1),
                data.get(2),
                Integer.valueOf(data.get(3)));
    }

    private String writeLine(Account account) {
        String list = SingletonUtils.getCSVRowDelimiter();
        list += account.getAccountNumber() + SingletonUtils.getCSVColumnDelimiter();
        list += account.getPin() + SingletonUtils.getCSVColumnDelimiter();
        list += account.getName() + SingletonUtils.getCSVColumnDelimiter();
        list += account.getBalance() + ";";

        return list;
    }
}
