package pages;

import pattern.IState;
import pattern.Singleton;
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
        super.controller = controller;
        super.nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {
        System.out.println("\n--Transfer Summary Screen--");
        System.out.println("Destination Account: " + destination);
        System.out.println("Transfer Amount: $" + amount);
        System.out.println("Reference Number: " + referenceNumber);
        System.out.println("Balance : $" + Singleton.getLoggedUser().getBalance());

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
                controller.nextState(Singleton.TransactionScreen());
                break;
            case WELCOME:
                controller.nextState(Singleton.WelcomeScreen());
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}
