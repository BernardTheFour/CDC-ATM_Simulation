package app.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import app.domains.Account;
import app.domains.Transaction;
import app.services.AccountService;
import app.services.TransactionService;

public class FileValidation {

    public static void validateFile() {
        try {
            validateAccount();
            validateTransaction();
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void validateAccount() throws Exception {
        List<Account> store = AccountService.getAll();
        Set<String> compare = new HashSet<>();
        List<Account> duplicates = new ArrayList<>();

        boolean isDuplicate = false;

        for (Account account : store) {
            if (!compare.add(account.getAccountNumber())) {
                isDuplicate = true;
                duplicates.add(account);
            }
        }
        if (isDuplicate) {
            System.out.println("\nDuplicate account number found!");
            duplicates.forEach(member -> System.out.println(member.toString()));
            throw new Exception("DataIntegrityException");
        }
    }

    private static void validateTransaction() throws Exception {
        List<Transaction> store = TransactionService.getAll();
        Set<String> compare = new HashSet<>();
        List<Transaction> duplicates = new ArrayList<>();

        boolean isDuplicate = false;

        for (Transaction transaction : store) {
            if (!compare.add(transaction.toString())) {
                isDuplicate = true;
                duplicates.add(transaction);
            }
        }
        if (isDuplicate) {
            System.out.println("\nDuplicate transaction found!");
            duplicates.forEach(member -> System.out.println(member.toString()));
            throw new Exception("DataIntegrityException");
        }
    }
}
