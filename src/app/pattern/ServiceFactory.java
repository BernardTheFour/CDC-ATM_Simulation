package app.pattern;

import app.entity.accounts.domain.Account;
import app.entity.accounts.service.AccountService;
import app.entity.transactions.domain.Transaction;
import app.entity.transactions.service.TransactionService;
import app.interfaces.IRepository;

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
