package util;

import java.io.File;
import java.io.FileNotFoundException;

import pattern.SingletonPath;

public class FileManagement {

    public static void extractPath(String path) throws FileNotFoundException {
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
            createFile("account.csv", SingletonPath.getPath());
            error = true;
        }

        if (SingletonPath.getTransactions() == null) {
            System.out.println("\nError: cannot find transactions.csv");
            createFile("transactions.csv", SingletonPath.getPath());
            error = true;
        }

        if (error) {
            throw new FileNotFoundException("\nCreating required file in the directory success");
        }
    }

    public static void createFile(String fileName, File path) {
        System.out.println("Create file " + fileName + " in " + path.getPath());
    }
}
