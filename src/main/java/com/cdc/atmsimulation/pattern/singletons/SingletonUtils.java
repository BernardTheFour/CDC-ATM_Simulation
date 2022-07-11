package com.cdc.atmsimulation.pattern.singletons;

import java.time.format.DateTimeFormatter;

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

    public static DateTimeFormatter getDateTimeFormat(){
        return instance.dateTimeFormat;
    }
}
