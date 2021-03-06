package com.cdc.atmsimulation.pattern;

import com.cdc.atmsimulation.entity.transactions.domain.Transaction;
import com.cdc.atmsimulation.entity.transactions.service.TransactionService;
import com.cdc.atmsimulation.entity.users.domain.Account;
import com.cdc.atmsimulation.entity.users.service.AccountService;
import com.cdc.atmsimulation.interfaces.IRepository;

public class ServiceFactory {

    private static AccountService accountService;
    private static TransactionService transactionService;

    public void setInstanceOfAccountService(IRepository<Account> repository) {
        
            accountService = new AccountService(repository);
    }

    public AccountService getInstanceOfAccountService() {
        if (accountService == null) {
            throw new NullPointerException("Account Service null");
        }
        return accountService;
    }

    public void setInstanceOfTransactionService(IRepository<Transaction> repository) {
            transactionService = new TransactionService(repository, accountService);
    }

    public TransactionService getInstanceOfTransactionService() {
        if (accountService == null) {
            throw new NullPointerException("Account Service null");
        }
        return transactionService;
    }
}
