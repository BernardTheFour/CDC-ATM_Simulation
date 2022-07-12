package com.cdc.atmsimulation.pages.consolepages;

import com.cdc.atmsimulation.entity.users.domain.Account;
import com.cdc.atmsimulation.interfaces.IState;
import com.cdc.atmsimulation.pattern.StateController;
import com.cdc.atmsimulation.pattern.singletons.SingletonScreen;

public class TransferSummaryScreen extends Page implements IState {

    public TransferSummaryScreen(StateController controller) {
        super(controller);
    }

    Account destination;
    int amount;
    int referenceNumber;

    public void setInfo(Account destination, int amount, int referenceNumber) {
        this.destination = destination;
        this.amount = amount;
        this.referenceNumber = referenceNumber;
    }

    @Override
    public void init() {
        System.out.println("\n-------------------------------");
        nextPage = Pages.DEFAULT;

        // write transaction
        services.getInstanceOfTransactionService()
                .addTransaction(loggedAccount, destination, amount);

        loggedAccount.setBalance(loggedAccount.getBalance() - amount);
    }

    @Override
    public void logic() {

        System.out.println("--Transfer Summary Screen--");
        System.out.println("Destination Account: "
                + destination.getName()
                + "(" + destination.getAccountNumber() + ")");
        System.out.println("Transfer Amount: $" + amount);
        System.out.println("Reference Number: " + referenceNumber);
        System.out.println("Balance : $" + loggedAccount.getBalance());

        System.out.println("\n1. Transaction");
        System.out.println("2. Exit");

        System.out.print("\nChoose option: ");
        String answer = cmdInput.nextLine();

        switch (answer) {
            case "1":
                nextPage = Pages.TRANSACTION;
                break;
            case "2":
                nextPage = Pages.WELCOME;
                break;
        }
    }

    @Override
    public void navigate() {
        switch (nextPage) {
            case TRANSACTION:
                controller.nextState(SingletonScreen.TransactionScreen());
                break;
            case WELCOME:
                controller.nextState(SingletonScreen.WelcomeScreen());
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}
