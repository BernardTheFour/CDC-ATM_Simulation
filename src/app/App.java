package app;

import java.io.File;
import java.io.IOException;

import app.entity.accounts.repository.FileRepoAccount;
import app.entity.transactions.repository.FileRepoTransaction;
import app.pattern.ServiceFactory;
import app.pattern.StateController;
import app.pattern.singletons.SingletonPath;
import app.pattern.singletons.SingletonScreen;
import app.pattern.singletons.SingletonUtils;
import app.util.CreateMissingFile;
import app.util.FileValidation;

public class App {

    public static void main(String[] args) {

        StateController screenNavigator = new StateController();

        Initialization(screenNavigator);

        CheckPath(args);

        ServiceFactory services = new ServiceFactory();
        ValidateFile(services);

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

    private static void ValidateFile(ServiceFactory services) {

        services.setInstanceOfAccountService(new FileRepoAccount(SingletonPath.getAccount()));
        services.setInstanceOfTransactionService(new FileRepoTransaction(SingletonPath.getTransactions()));

        FileValidation validation = new FileValidation();
        try {
            validation.validateAccount(services.getInstanceOfAccountService());
            validation.validateTransaction(services.getInstanceOfTransactionService());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
