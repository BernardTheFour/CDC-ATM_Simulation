package app;

import java.io.File;
import java.io.IOException;

import app.pattern.SingletonPath;
import app.pattern.SingletonScreen;
import app.pattern.SingletonUtils;
import app.pattern.StateController;
import app.services.AccountService;
import app.services.TransactionService;
import app.util.CreateMissingFile;
import app.util.FileValidation;

public class App {

    public static void main(String[] args) {

        StateController screenNavigator = new StateController();

        Initialization(screenNavigator);

        CheckPath(args);

        StartService();

        FileValidation.validateFile();

        screenNavigator.firstState(SingletonScreen.WelcomeScreen());
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

        try {
            CreateMissingFile.extractPath(path[0]);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void Initialization(StateController screenNavigator) {
        System.out.println("\nProgram starting...");
        SingletonPath.init();
        SingletonScreen.init(screenNavigator);
        SingletonUtils.init();
    }

    private static void StartService() {
        new AccountService(SingletonPath.getAccount());
        new TransactionService(SingletonPath.getTransactions());
    }
}
