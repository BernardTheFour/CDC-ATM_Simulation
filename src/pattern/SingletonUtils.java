package pattern;

import java.time.format.DateTimeFormatter;

import csv_access.CSVAccount;
import csv_access.CSVTransaction;

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

    private CSVAccount csvAccount;
    private CSVTransaction csvTransaction;

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

    public static void setCSVAccount(CSVAccount cAccount) {
        instance.csvAccount = cAccount;
    }

    public static CSVAccount getCSVAccount() {
        return instance.csvAccount;
    }

    public static void setCSVTransaction(CSVTransaction cTransaction){
        instance.csvTransaction = cTransaction;
    }

    public static CSVTransaction getCSVTransaction(){
        return instance.csvTransaction;
    }

    public static DateTimeFormatter getDateTimeFormat(){
        return instance.dateTimeFormat;
    }
}
