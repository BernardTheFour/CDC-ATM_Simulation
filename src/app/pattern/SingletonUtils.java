package app.pattern;

import java.time.format.DateTimeFormatter;

import app.repository.fileImpl.FileRepositoryAccount;
import app.repository.fileImpl.FileRepositoryTransaction;

public class SingletonUtils {

    private static SingletonUtils instance;

    /*
     * windows EOL delimiter \r\n
     * Linux and Mac EOL delimiter \n
     * Change before deploy to different OS
     */
    private String csvRowDelimiter = "\r\n";
    private String csvColumnDelimiter = ";";

    private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("hh:mm a dd-MM-yyyy");

    private FileRepositoryAccount csvAccount;
    private FileRepositoryTransaction csvTransaction;

    public static void init() {
        if (instance == null) {
            instance = new SingletonUtils();
        }
    }

    public static String getCSVRowDelimiter() {
        return instance.csvRowDelimiter;
    }

    public static String getCSVColumnDelimiter(){
        return instance.csvColumnDelimiter;
    }

    public static void setCSVAccount(FileRepositoryAccount cAccount) {
        instance.csvAccount = cAccount;
    }

    public static FileRepositoryAccount getCSVAccount() {
        return instance.csvAccount;
    }

    public static void setCSVTransaction(FileRepositoryTransaction cTransaction){
        instance.csvTransaction = cTransaction;
    }

    public static FileRepositoryTransaction getCSVTransaction(){
        return instance.csvTransaction;
    }

    public static DateTimeFormatter getDateTimeFormat(){
        return instance.dateTimeFormat;
    }
}
