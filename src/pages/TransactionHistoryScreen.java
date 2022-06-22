package pages;


import domains.Transaction;
import pattern.IState;
import pattern.SingletonData;
import pattern.SingletonScreen;
import pattern.SingletonUtils;
import pattern.StateController;

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

        for (Transaction transaction : SingletonData.getTransactions()) {
            String row = "";
            String transferName = "-\t";

            if (!transaction.getTransferTo().equals("null")) {
                transferName = "(" + transaction.getTransferTo() + ") ";
                transferName += SingletonUtils.getCSVAccount().getById(transaction.getTransferTo()).get().getName();
            }

            row += "$" + transaction.getAmount() + "\t";
            row += transaction.getTransactionType() + "\t";
            row += transferName + "\t";
            row += transaction.getDate().format(SingletonUtils.getDateTimeFormat());

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
