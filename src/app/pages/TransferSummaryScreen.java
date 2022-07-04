package app.pages;


import app.domains.Account;
import app.pattern.IState;
import app.pattern.SingletonData;
import app.pattern.SingletonScreen;
import app.pattern.StateController;

public class TransferSummaryScreen extends Page implements IState {

    Account destination;
    int amount;
    int referenceNumber;

    public void setInfo(Account destination, int amount, int referenceNumber) {
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

        System.out.println("--Transfer Summary Screen--");
        System.out.println("Destination Account: "
                + destination.getName()
                + "(" + destination.getAccountNumber() + ")");
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
