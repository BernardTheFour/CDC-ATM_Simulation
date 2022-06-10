package pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import pattern.IState;
import pattern.Singleton;
import pattern.StateController;

public class SummaryScreen extends Page implements IState {

    private int withdrawAmount;

    public void setInfo(int withdrawAmount){
        this.withdrawAmount = withdrawAmount;
    }

    @Override
    public void init(StateController controller) {
        super.controller = controller;
        super.nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {
        System.out.println("\n--Summary--");
        System.out.printf("Date: %s%n",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern(Singleton.getDateFormat())));
        System.out.println("Withdraw: $" + withdrawAmount);
        System.out.println("Balance: $" + Singleton.getLoggedUser().getBalance());

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
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}
