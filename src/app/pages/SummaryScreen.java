package app.pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import app.domains.Transaction;
import app.domains.Transaction.Type;
import app.pattern.IState;
import app.pattern.SingletonScreen;
import app.pattern.StateController;
import app.services.TransactionService;

public class SummaryScreen extends Page implements IState {

    public SummaryScreen(StateController controller) {
        super(controller);
    }

    private int amount;

    public void setInfo(int amount) {
        this.amount = amount;
    }

    @Override
    public void init() {
        System.out.println("\n-------------------------------");
        nextPage = Pages.DEFAULT;

        // write transaction
        Transaction transaction = new Transaction(
                loggedAccount.getAccountNumber(),
                Type.WITHDRAW,
                null,
                amount,
                LocalDateTime.now());

        services.getInstanceOfTransactionService().addTransaction(transaction);

        loggedAccount.setBalance(loggedAccount.getBalance() - amount);
    }

    @Override
    public void logic() {
        System.out.println("--Summary--");
        System.out.printf("Date: %s%n",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern(SingletonScreen.getDateFormat())));
        System.out.println("Withdraw: $" + amount);
        System.out.println("Balance: $" + loggedAccount.getBalance());

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
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}
