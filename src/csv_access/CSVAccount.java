package csv_access;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domains.Account;
import pattern.SingletonData;
import pattern.SingletonPath;
import pattern.SingletonUtils;

public class CSVAccount implements IFileManipulation<Account> {

    private DataAccess dataAccess;

    public CSVAccount(File file) {
        dataAccess = new DataAccess(file);
    }

    @Override
    public Optional<Account> getById(String id) {
        Optional<String> result = dataAccess.getById(id);

        if (result.isEmpty()) {
            System.out.printf("failed: account %s not found%n", id);
            return Optional.empty();
        }

        List<String> data = List.of(result.get().split(SingletonUtils.getCSVColumnDelimiter()));

        return Optional.of(read(data));
    }

    @Override
    public Optional<List<Account>> getAll() {
        Optional<List<String>> result = dataAccess.getAll();
        List<Account> data = new ArrayList<>();

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
    public void edit(Account data) {
        SingletonData.getAccounts().forEach(member -> {
            if (member.getAccountNumber().equals(data.getAccountNumber())) {
                member.setBalance(data.getBalance());
            }
        });
    }

    @Override
    public void save() {
        String list = "";

        for (Account account : SingletonData.getAccounts()) {
            list += SingletonUtils.getCSVRowDelimiter();
            list += account.getAccountNumber() + SingletonUtils.getCSVColumnDelimiter();
            list += account.getPin() + SingletonUtils.getCSVColumnDelimiter();
            list += account.getName() + SingletonUtils.getCSVColumnDelimiter();
            list += account.getBalance();
        }

        SingletonPath.setAccount(
                dataAccess.save(SingletonPath.getAccount(), list));
    }

    private Account read(List<String> data) {
        Account account = new Account();
        account.setAccountNumber(data.get(0));
        account.setPin(data.get(1));
        account.setName(data.get(2));
        account.setBalance(Integer.valueOf(data.get(3)));

        return account;
    }
}
