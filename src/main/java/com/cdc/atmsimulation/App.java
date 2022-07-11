package com.cdc.atmsimulation;

import java.io.File;
import java.io.IOException;

import com.cdc.atmsimulation.entity.accounts.repository.FileRepoAccount;
import com.cdc.atmsimulation.entity.transactions.repository.FileRepoTransaction;
import com.cdc.atmsimulation.pattern.ServiceFactory;
import com.cdc.atmsimulation.pattern.StateController;
import com.cdc.atmsimulation.pattern.singletons.SingletonFile;
import com.cdc.atmsimulation.pattern.singletons.SingletonScreen;
import com.cdc.atmsimulation.pattern.singletons.SingletonUtils;
import com.cdc.atmsimulation.util.CreateMissingFile;
import com.cdc.atmsimulation.util.FileValidation;

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
        SingletonFile.init();
        SingletonScreen.init(screenNavigator);
        SingletonUtils.init();
    }

    private static void ValidateFile(ServiceFactory services) {

        services.setInstanceOfAccountService(new FileRepoAccount(SingletonFile.getAccount()));
        services.setInstanceOfTransactionService(new FileRepoTransaction(SingletonFile.getTransactions()));

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
