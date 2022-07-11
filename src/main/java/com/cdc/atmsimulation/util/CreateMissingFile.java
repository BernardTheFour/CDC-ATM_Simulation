package com.cdc.atmsimulation.util;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;

import com.cdc.atmsimulation.pattern.singletons.SingletonFile;

public class CreateMissingFile {

    public static void extractPath(String path) throws IOException {
        SingletonFile.setPath(new File(path));

        File[] files = SingletonFile.getPath().listFiles();

        for (File file : files) {
            String fileName = file.getName();

            if ("account.csv".equals(fileName)) {
                SingletonFile.setAccount(file);
            }

            if ("transaction.csv".equals(fileName)) {
                SingletonFile.setTransactions(file);
            }
        }

        boolean error = false;
        if (SingletonFile.getAccount() == null) {
            System.out.println("\nError: cannot find account.csv");
            System.out.println("Creating account.csv file");
            error = true;

            String[] columns = { "Account Number", "Pin", "Name", "Balance" };
            File file = createFile(columns, "account.csv", path);
            SingletonFile.setAccount(file);
        }

        if (SingletonFile.getTransactions() == null) {
            System.out.println("\nError: cannot find transaction.csv");
            System.out.println("Creating transaction.csv file");
            error = true;

            String[] columns = { "Account Number", "Type", "From/To", "Amount", "Date" };
            File file = createFile(columns, "transaction.csv", path);
            SingletonFile.setTransactions(file);
        }

        if (error) {
            System.out.println("\nSuccessfully creating required file in the directory");
        }
    }

    public static File createFile(String[] columns, String fileName, String path) throws IOException {
        File file = new File(path + "\\" + fileName);
        file.createNewFile();

        String line = "";
        for (String column : columns) {
            line += column + ";";
        }

        Files.writeString(file.toPath(), line);

        return file;
    }
}
