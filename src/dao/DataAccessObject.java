package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pattern.SingletonUtils;

public class DataAccessObject {

    private final File file;
    private final Scanner scanner;

    public DataAccessObject(File file) throws FileNotFoundException {
        this.file = file;
        scanner = new Scanner(this.file);
    }

    protected Object[] readRow(int row) {
        try {
            scanner.useDelimiter(SingletonUtils.getCSVRowDelimiter());

            int currentRow = 0;
            while (scanner.hasNext()) {
                if (currentRow == row) {
                    break;
                }
                scanner.next();
                currentRow++;
            }

            return scanner.next().split(";");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void writeRow(int row) {

    }

    protected Object[] searchStringByColumn(int column, String name) {
        return null;
    }

    protected Object[] searchIntByColumn(int column, int value) {
        return null;
    }
}
