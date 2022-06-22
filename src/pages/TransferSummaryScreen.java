package pages;

import java.time.LocalDateTime;

import domains.Transaction;
import domains.Transaction.Type;
import pattern.IState;
import pattern.SingletonData;
import pattern.SingletonScreen;
import pattern.SingletonUtils;
import pattern.StateController;

public class TransferSummaryScreen extends Page implements IState {

    String destination;
    int amount;
    int referenceNumber;

    public void setInfo(String destination, int amount, int referenceNumber) {
        this.destination = destination;
        this.amount = amount;
        this.referenceNumber = referenceNumber;
    }

    @Override
    public void init(StateController controller) {
        System.out.println("\n-------------------------------");
        super.controller = controller;
        super.nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {
        // write transaction
        Transaction transactionSender = new Transaction(
                SingletonData.getLoggedUser().getAccountNumber(),
                Type.TRANSFER,
                destination,
                amount,
                LocalDateTime.now());
        Transaction transactionReceiver = new Transaction(
                destination, Type.RECEIVE,
                SingletonData.getLoggedUser().getAccountNumber(),
                amount,
                LocalDateTime.now());

        SingletonUtils.getCSVTransaction().add(transactionSender);
        SingletonUtils.getCSVTransaction().add(transactionReceiver);

        System.out.println("--Transfer Summary Screen--");
        System.out.println("Destination Account: " + destination);
        System.out.println("Transfer Amount: $" + amount);
        System.out.println("Reference Number: " + referenceNumber);
        System.out.println("Balance : $" + SingletonData.getLoggedUser().getBalance());

        System.out.println("\n1. Transaction");
        System.out.println("2. Exit");

        System.out.print("\nChoose option: ");
        String answer = super.input.nextLine();

        switch (answer) {
            case "1":
                super.nextPage = Pages.TRANSACTION;
                break;
            case "2":
                super.nextPage = Pages.WELCOME;
                break;
        }
    }

    @Override
    public void navigate() {
        switch (super.nextPage) {
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
