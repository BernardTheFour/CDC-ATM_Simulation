package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import pattern.SingletonPath;

public class FileManagement {

    public static void extractPath(String path) throws FileNotFoundException, IOException {
        SingletonPath.setPath(new File(path));

        File[] files = SingletonPath.getPath().listFiles();

        for (File file : files) {
            String fileName = file.getName();

            if ("account.csv".equals(fileName)) {
                SingletonPath.setAccount(file);
            }

            if ("transaction.csv".equals(fileName)) {
                SingletonPath.setTransactions(file);
            }
        }

        boolean error = false;
        if (SingletonPath.getAccount() == null) {
            System.out.println("\nError: cannot find account.csv");
            createAccountFile(path);
            error = true;
        }

        if (SingletonPath.getTransactions() == null) {
            System.out.println("\nError: cannot find transaction.csv");
            createTransactionFile(path);
            error = true;
        }

        if (error) {
            throw new FileNotFoundException("\nSuccessfully creating required file in the directory");
        }
    }

    private static void createAccountFile(String path) throws IOException {
        File file = new File(path + "\\account.csv");
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("Account Number");
        writer.write("Pin");
        writer.write("Name");
        writer.write("Balance");
        writer.newLine();

        writer.flush();
        writer.close();
    }

    private static void createTransactionFile(String path) throws IOException {
        File file = new File(path + "\\transaction.csv");
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("Account Number;");
        writer.write("Type;");
        writer.write("Transfer To;");
        writer.write("Amount;");
        writer.write("Date;");

        writer.flush();
        writer.close();
    }
}
