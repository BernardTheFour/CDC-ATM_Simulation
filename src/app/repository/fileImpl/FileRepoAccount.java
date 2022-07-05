package app.repository.fileImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<List<String>> result = fileManager.getAll();
        List<Account> data = new ArrayList<>();

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
    public void edit(Account data) {
        Account.get().forEach(member -> {
            if (member.getAccountNumber().equals(data.getAccountNumber())) {
                member.setBalance(data.getBalance());
            }
        });
    }

    @Override
    public void save() {
        String list = "";

        for (Account account : Account.get()) {
           list += writeLine(account);
        }

        SingletonPath.setAccount(
                fileManager.save(SingletonPath.getAccount(), list));
    }

    @Override
    public void add(Account data) {
        throw new UnsupportedOperationException("Not implemented yet");        
    }

    private Account readLine(List<String> data) {
        Account account = new Account(
            data.get(0),
            data.get(1),
            data.get(2),
            Integer.valueOf(data.get(3))
        );

        return account;
    }

    private String writeLine(Account account){
        String list = "";
        list += account.getAccountNumber() + SingletonUtils.getCSVColumnDelimiter();
        list += account.getPin() + SingletonUtils.getCSVColumnDelimiter();
        list += account.getName() + SingletonUtils.getCSVColumnDelimiter();
        list += account.getBalance() + SingletonUtils.getCSVRowDelimiter();

        return list;
    }

    @Override
    public List<Account> getAllById(String id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
