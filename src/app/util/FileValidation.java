package app.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import app.entity.accounts.domain.Account;
import app.entity.accounts.service.AccountService;
import app.entity.transactions.domain.Transaction;
import app.entity.transactions.service.TransactionService;

public class FileValidation {

    public void validateAccount(AccountService service) throws Exception {
        Set<String> compare = new HashSet<>();
        List<Account> duplicates = new ArrayList<>();

        service.getAll().stream()
                .forEach(i -> {
                    if (!compare.add(i.getAccountNumber())) {
                        duplicates.add(i);
                    }
                });

        if (duplicates.size() > 0) {
            System.out.println("\nDuplicate account number found!");
            duplicates.forEach(member -> System.out.println(member.toString()));
            throw new Exception("DataIntegrityException");
        }
    }

    public void validateTransaction(TransactionService service) throws Exception {
        Set<String> compare = new HashSet<>();
        List<Transaction> duplicates = new ArrayList<>();

        service.getAll().stream()
                .forEach(i -> {
                    if (!compare.add(i.toString())) {
                        duplicates.add(i);
                    }
                });

        if (duplicates.size() > 0) {
            System.out.println("\nDuplicate transaction found!");
            duplicates.forEach(member -> System.out.println(member.toString()));
            throw new Exception("DataIntegrityException");
        }
    }
}
