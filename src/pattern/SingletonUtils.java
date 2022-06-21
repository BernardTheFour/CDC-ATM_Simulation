package pattern;

import csv_access.CSVAccount;

public class SingletonUtils {

    private static SingletonUtils instance;

    /*
     * windows EOL delimiter \r\n
     * Linux and Mac EOL delimiter \n
     * Change before deploy to different OS
     */
    private String csvRowDelimiter = "\r\n";
    private String csvColumnDelimiter = ";";

    private CSVAccount csvAccount;

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

    public static CSVAccount getCsvAccount() {
        return instance.csvAccount;
    }
}
