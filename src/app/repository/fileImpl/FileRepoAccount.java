package app.repository.fileImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import app.domains.Account;
import app.pattern.SingletonPath;
import app.pattern.SingletonUtils;
import app.repository.IRepository;
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
    public Stream<Account> edit(Account data) {
        return getAll().get().stream()
                .map(x -> {
                    if (x.getAccountNumber().equals(data.getAccountNumber())) {
                        x.setBalance(data.getBalance());
                    }
                    return x;
                });
    }

    @Override
    public void save(Stream<Account> stream) {
        StringBuilder sb = new StringBuilder();

        stream.forEach(i -> sb.append(writeLine(i)));

        SingletonPath.setAccount(
                fileManager.save(SingletonPath.getAccount(), sb.toString()));
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
        Account account = new Account(
                data.get(0),
                data.get(1),
                data.get(2),
                Integer.valueOf(data.get(3)));

        return account;
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
