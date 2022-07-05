package app.pages;

import app.domains.Transaction;
import app.pattern.IState;
import app.pattern.SingletonScreen;
import app.pattern.SingletonUtils;
import app.pattern.StateController;
import app.services.AccountService;

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

        for (int i = 0; i < Math.max(10, Transaction.getData().size()); i++) {
            String row = "";
            String transferName = "-\t";

            if (!Transaction.getData().get(i).getAssociate().equals("null")) {
                transferName = "(" + Transaction.getData().get(i) + ") ";
                transferName += AccountService.getById(Transaction.getData().get(i).getAssociate()).getName();
            }

            row += "$" + Transaction.getData().get(i).getAmount() + "\t";
            row += Transaction.getData().get(i).getTransactionType() + "\t";
            row += transferName + "\t";
            row += Transaction.getData().get(i).getDate().format(SingletonUtils.getDateTimeFormat());

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
