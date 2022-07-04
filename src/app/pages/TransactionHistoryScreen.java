package app.pages;

import app.domains.Transaction;
import app.pattern.IState;
import app.pattern.SingletonScreen;
import app.pattern.SingletonUtils;
import app.pattern.StateController;
import app.services.AccountService;

public class TransactionHistoryScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        System.out.println("\n-------------------------------");
        super.controller = controller;
        super.nextPage = Pages.DEFAULT;

    }

    @Override
    public void logic() {
        System.out.println("--Transaction History--");

        for (int i = 0; i < Math.max(10, Transaction.instance().size()); i++) {
            String row = "";
            String transferName = "-\t";

            if (!Transaction.instance().get(i).getAssociate().equals("null")) {
                transferName = "(" + Transaction.instance().get(i) + ") ";
                transferName += AccountService.getById(Transaction.instance().get(i).getAssociate()).getName();
            }

            row += "$" + Transaction.instance().get(i).getAmount() + "\t";
            row += Transaction.instance().get(i).getTransactionType() + "\t";
            row += transferName + "\t";
            row += Transaction.instance().get(i).getDate().format(SingletonUtils.getDateTimeFormat());

            System.out.println(row);
        }

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
