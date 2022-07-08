package app.pages;

import java.util.List;

import app.domains.Transaction;
import app.pattern.IState;
import app.pattern.SingletonScreen;
import app.pattern.SingletonUtils;
import app.pattern.StateController;
import app.services.AccountService;
import app.services.TransactionService;

public class TransactionHistoryScreen extends Page implements IState {

    public TransactionHistoryScreen(StateController controller) {
        super(controller);
    }

    @Override
    public void init() {
        System.out.println("\n-------------------------------");
        nextPage = Pages.DEFAULT;

    }

    @Override
    public void logic() {
        System.out.println("--Transaction History--");

        List<Transaction> transactions = services.getInstanceOfTransactionService()
                .getAllById(loggedAccount.getAccountNumber());

        int max = Math.min(10, transactions.size());
        for (int i = 0; i < max; i++) {
            String row = "";
            String transferName = "-\t\t";

            if (!transactions.get(i).getAssociate().equals("null")) {
                transferName = "(" + transactions.get(i).getAccount() + ") ";
                transferName += services.getInstanceOfAccountService()
                        .getById(transactions.get(i).getAssociate()).getName();
            }

            row += "$" + transactions.get(i).getAmount() + "\t";
            row += transactions.get(i).getTransactionType() + "\t";
            row += transferName + "\t";
            row += transactions.get(i).getDate().format(SingletonUtils.getDateTimeFormat());

            System.out.println(row);
        }

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
