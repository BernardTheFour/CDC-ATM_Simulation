package app.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import app.pattern.SingletonPath;

public class CreateMissingFile {

    public static void extractPath(String path) throws IOException {
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
            System.out.println("Creating account.csv file");
            error = true;

            String[] columns = { "Account Number", "Pin", "Name", "Balance" };
            File file = createFile(columns, "account.csv", path);
            SingletonPath.setAccount(file);
        }

        if (SingletonPath.getTransactions() == null) {
            System.out.println("\nError: cannot find transaction.csv");
            System.out.println("Creating transaction.csv file");
            error = true;

            String[] columns = { "Account Number", "Type", "From/To", "Amount", "Date" };
            File file = createFile(columns, "transaction.csv", path);
            SingletonPath.setTransactions(file);
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
