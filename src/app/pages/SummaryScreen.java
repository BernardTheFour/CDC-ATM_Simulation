package app.pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import app.domains.Transaction;
import app.domains.Transaction.Type;
import app.pattern.IState;
import app.pattern.SingletonData;
import app.pattern.SingletonScreen;
import app.pattern.StateController;
import app.services.TransactionService;

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

        TransactionService.addTransaction(transaction);

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
