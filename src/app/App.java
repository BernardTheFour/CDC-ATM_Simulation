package app;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import app.domains.Account;
import app.domains.Transaction;
import app.pattern.SingletonData;
import app.pattern.SingletonPath;
import app.pattern.SingletonScreen;
import app.pattern.SingletonUtils;
import app.pattern.StateController;
import app.repository.file_implementation.FileRepositoryAccount;
import app.repository.file_implementation.FileRepositoryTransaction;
import app.util.CreateMissingFile;
import app.util.FileValidation;

public class App {

    private static StateController screenNavigator;

    public static void main(String[] args) {

        CheckPath(args);

        Initialization();

        try {
            CreateMissingFile.extractPath(args[0]);

        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.out.println(e.getMessage());
            } else {
                e.printStackTrace();
                System.exit(0);
            }
        }

        FileRepositoryAccount csvAccount = new FileRepositoryAccount(SingletonPath.getAccount());
        FileRepositoryTransaction csvTransaction = new FileRepositoryTransaction(SingletonPath.getTransactions());

        SingletonUtils.setCSVTransaction(csvTransaction);
        SingletonUtils.setCSVAccount(csvAccount);
        Account.set(new ArrayList<>(SingletonUtils.getCSVAccount().getAll().get()));

        FileValidation.validateFile();

        screenNavigator = new StateController(
                SingletonScreen.WelcomeScreen());

        screenNavigator.run();
    }

    private static void CheckPath(String[] path) {
        File file = path.length > 0 ? new File(path[0]) : null;

        if (file == null) {
            System.out.println("\nPlease input directory path argument\n");
            System.exit(0);
        }

        if (!file.isDirectory()) {
            System.out.println("Pathfile should be folder directory, not file.");
            System.exit(0);
        }
    }

    private static void Initialization() {
        System.out.println("\nProgram starting...");
        SingletonPath.init();
        SingletonData.init();
        SingletonScreen.init();
        SingletonUtils.init();
        new Account();
        new Transaction();
    }
}
