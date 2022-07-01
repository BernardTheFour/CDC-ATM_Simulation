package app.pattern;

import app.domains.Account;

public class SingletonData {    
    
    private static SingletonData instance;

    private Account loggedUser = new Account();

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
}
