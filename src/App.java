import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import csv_access.CSVAccount;
import domains.Account;
import pattern.SingletonData;
import pattern.SingletonPath;
import pattern.SingletonScreen;
import pattern.SingletonUtils;
import pattern.StateController;
import util.FileManagement;

public class App {

    private static StateController screenNavigator;

    public static void main(String[] args) {

        CheckPath(args);

        Initialization();

        try {
            FileManagement.extractPath(args[0]);

        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.out.println(e.getMessage());
            } else {
                e.printStackTrace();
                System.exit(0);
            }
        }

        CSVAccount csvAccount = new CSVAccount(SingletonPath.getAccount());


        SingletonUtils.setCSVAccount(csvAccount);
        SingletonData.setAccounts(new HashSet<>(SingletonUtils.getCsvAccount().getAll().get()));

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
    }
}
