package com.cdc.atmsimulation;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cdc.atmsimulation.entity.accounts.repository.FileRepoAccount;
import com.cdc.atmsimulation.entity.transactions.repository.FileRepoTransaction;
import com.cdc.atmsimulation.pattern.ServiceFactory;
import com.cdc.atmsimulation.pattern.StateController;
import com.cdc.atmsimulation.pattern.singletons.SingletonFile;
import com.cdc.atmsimulation.pattern.singletons.SingletonScreen;
import com.cdc.atmsimulation.pattern.singletons.SingletonUtils;
import com.cdc.atmsimulation.util.CreateMissingFile;
import com.cdc.atmsimulation.util.FileValidation;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        CheckArgs(args);

        if (args[1].equals("-web".toString())) {
            SpringApplication.run(App.class, args);
        }

        if (args[1].equals("-console".toString())) {
            StateController screenNavigator = new StateController();

            SingletonUtils.init();
            SingletonScreen.init(screenNavigator);

            ServiceFactory services = new ServiceFactory();
            ValidateFile(services);

            screenNavigator.firstState(SingletonScreen.WelcomeScreen());
            screenNavigator.run();
        }
    }

    private static void CheckArgs(String[] args) {
        if (args.length > 0 && args.length < 2) {
            System.out.println("ERROR: Arguments missing -web or -console");
            System.exit(0);
        }

        File file = args.length > 0 ? new File(args[0]) : null;

        if (file == null) {
            System.out.println("\nERROR: Please input directory path argument\n");
            System.exit(0);
        }

        if (!file.isDirectory()) {
            System.out.println("ERROR: Pathfile should be folder directory, not file.");
            System.exit(0);
        }

        try {
            SingletonFile.init();
            CreateMissingFile.extractPath(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
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
