package pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import domains.Transaction;
import domains.Transaction.Type;
import pattern.IState;
import pattern.SingletonData;
import pattern.SingletonScreen;
import pattern.SingletonUtils;
import pattern.StateController;

public class SummaryScreen extends Page implements IState {

    private int withdrawAmount;

    public void setInfo(int withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
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
        Transaction transaction = new Transaction(
                SingletonData.getLoggedUser().getAccountNumber(),
                Type.WITHDRAW,
                null,
                withdrawAmount,
                LocalDateTime.now());

        SingletonUtils.getCSVTransaction().add(transaction);

        System.out.println("--Summary--");
        System.out.printf("Date: %s%n",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern(SingletonScreen.getDateFormat())));
        System.out.println("Withdraw: $" + withdrawAmount);
        System.out.println("Balance: $" + SingletonData.getLoggedUser().getBalance());

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
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}
