package pattern;

import java.io.File;

public class SingletonPath {
    private static SingletonPath instance;

    private String generalPath;

    private File accounts;
    private File transactions;

    public static void init() {
        if (instance == null) {
            instance = new SingletonPath();
        }
    }

    public static void setPath(String path){
        instance.generalPath = path;
    }

    public String getPath(String path){
        return instance.generalPath;
    }

    public static void setAccount(File file){
        instance.accounts = file;
    }

    public File getAccount(){
        return instance.accounts;
    }

    public String getGeneralPath() {
        return this.generalPath;
    }

    public void setGeneralPath(String generalPath) {
        this.generalPath = generalPath;
    }

    public File getAccounts() {
        return this.accounts;
    }

    public void setAccounts(File accounts) {
        this.accounts = accounts;
    }

    public File getTransactions() {
        return this.transactions;
    }

    public void setTransactions(File transactions) {
        this.transactions = transactions;
    }
}
