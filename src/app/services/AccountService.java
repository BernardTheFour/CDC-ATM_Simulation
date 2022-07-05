package app.services;

import java.io.File;
import java.util.List;

import app.domains.Account;
import app.repository.fileImpl.FileRepoAccount;

public class AccountService {

    private static AccountService instance;

    private static FileRepoAccount fileRepoAccount;

    public AccountService(){}

    public AccountService(File file){
        if (instance == null){
            instance = new AccountService();
            fileRepoAccount = new FileRepoAccount(file);
        }
    }

    public static List<Account> getAll(){
        return fileRepoAccount.getAll().get();
    }

    public static Account getById(String associate) {
        return fileRepoAccount.getById(associate).get();
    }

    public static void editAccount(Account account) {
        fileRepoAccount.edit(account);
        fileRepoAccount.save();
    }
}