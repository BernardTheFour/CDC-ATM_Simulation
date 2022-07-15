package com.cdc.atmsimulation.entity.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdc.atmsimulation.entity.users.domain.Account;
import com.cdc.atmsimulation.interfaces.IRepository;

@Service
public class AccountService {

    @Autowired
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
