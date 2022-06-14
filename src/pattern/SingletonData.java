package pattern;

import java.util.HashSet;
import java.util.Set;

import domains.Account;

public class SingletonData {    
    
    private static SingletonData instance;

    private Account loggedUser = new Account();;
    private Set<Account> accounts = new HashSet<>();

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
}
