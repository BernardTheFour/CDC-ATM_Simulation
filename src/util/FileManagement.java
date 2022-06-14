package util;

import java.io.File;
import java.io.FileNotFoundException;

import pattern.SingletonPath;

public class FileManagement {

    public static void extractPath(String path) throws FileNotFoundException {
        SingletonPath.setPath(path);

        File[] files = new File(path).listFiles();

        for (File file : files) {
            String fileName = file.getName();

            if (!"account.csv".equals(fileName)){
                throw new FileNotFoundException("error: cannot find account.csv");
            }

            if (!"transaction.csv".equals(fileName)){
                throw new FileNotFoundException("error: cannot find transaction.csv");
            }
        }
    }

    public static void createFile(String fileName){

    }
}
