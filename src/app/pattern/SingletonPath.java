package app.pattern;

import java.io.File;

public class SingletonPath {
    private static SingletonPath instance;

    private File generalPath;

    private File accounts;
    private File transactions;

    public static void init() {
        if (instance == null) {
            instance = new SingletonPath();
        }
    }

    public static void setPath(File path){
        instance.generalPath = path;
    }

    public static File getPath(){
        return instance.generalPath;
    }

    public static void setAccount(File fileAccount){
        instance.accounts = fileAccount;
    }

    public static File getAccount(){
        return instance.accounts;
    }

    public static File getTransactions() {
        return instance.transactions;
    }

    public static void setTransactions(File transactions) {
        instance.transactions = transactions;
    }
}
