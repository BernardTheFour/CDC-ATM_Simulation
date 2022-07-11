package com.cdc.atmsimulation.entity.accounts.service;

import java.util.List;

import com.cdc.atmsimulation.entity.accounts.domain.Account;
import com.cdc.atmsimulation.interfaces.IRepository;

public class AccountService {

    private IRepository<Account> repository;

    public AccountService(IRepository<Account> repository) {
        this.repository = repository;
    }

    public List<Account> getAll() {
        return repository.getAll().get();
    }

    public Account getById(String associate) {
        return repository.getById(associate).get();
    }

    public void editAccount(Account account) {
        repository.edit(account);
    }
}
