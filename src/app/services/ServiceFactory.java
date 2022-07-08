package app.services;

import app.domains.Account;
import app.domains.Transaction;
import app.repository.IRepository;

public class ServiceFactory {

    private static AccountService accountService;
    private static TransactionService transactionService;

    public void setInstanceOfAccountService(IRepository<Account> repository) {
        if (accountService == null) {
            accountService = new AccountService(repository);
        }
    }

    public AccountService getInstanceOfAccountService() {
        if (accountService == null) {
            throw new NullPointerException("Account Service null");
        }
        return accountService;
    }

    public void setInstanceOfTransactionService(IRepository<Transaction> repository) {
        if (transactionService == null) {
            transactionService = new TransactionService(repository, accountService);
        }
    }

    public TransactionService getInstanceOfTransactionService() {
        if (accountService == null) {
            throw new NullPointerException("Account Service null");
        }
        return transactionService;
    }
}
