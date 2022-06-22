package pattern;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import domains.Account;
import domains.Transaction;

public class SingletonData {    
    
    private static SingletonData instance;

    private Account loggedUser = new Account();;
    private Set<Account> accounts = new LinkedHashSet<>();
    private List<Transaction> transactions = new ArrayList<>();

    public static void init() {
        if (instance == null) {
            instance = new SingletonData();
        }
    }

    public static Account getLoggedUser() {
        return instance.loggedUser;
    }

    public static void setLoggedUser(Account loggedUser) {
        instance.loggedUser = loggedUser;
    }

    public static Set<Account> getAccounts() {
        return instance.accounts;
    }

    public static void setAccounts(Set<Account> accounts) {
        instance.accounts = accounts;
    }

    public static List<Transaction> getTransactions(){
        return instance.transactions;
    }

    public static void setTransactions(List<Transaction> transactions){
        instance.transactions = transactions;
    }
}
