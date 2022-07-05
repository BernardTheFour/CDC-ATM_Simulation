package app.pages;

import app.domains.Transaction;
import app.pattern.IState;
import app.pattern.SingletonScreen;
import app.pattern.StateController;
import app.services.TransactionService;

public class TransactionScreen extends Page implements IState {

    public TransactionScreen(StateController controller) {
        super(controller);
    }

    @Override
    public void init() {
        System.out.println("\n-------------------------------");
        nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {
        // get all transaction information
        Transaction.setData(
                TransactionService.getAllById(loggedAccount.getAccountNumber()));

        System.out.println("Balance: " + loggedAccount.getBalance() + "\n");
        System.out.println("--Transactions--");
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Transaction History");
        System.out.println("4. Exit");

        System.out.print("\nChoose option: ");

        String answer = cmdInput.nextLine();

        switch (answer) {
            case "1":
                nextPage = Pages.WITHDRAW;
                break;
            case "2":
                nextPage = Pages.TRANSFER;
                break;
            case "3":
                nextPage = Pages.TRANSACTION_HISTORY;
                break;
            case "4":
                nextPage = Pages.WELCOME;
                break;
        }
    }

    @Override
    public void navigate() {
        switch (nextPage) {
            case WELCOME:
                controller.nextState(SingletonScreen.WelcomeScreen());
                break;
            case WITHDRAW:
                controller.nextState(SingletonScreen.WithdrawScreen());
                break;
            case TRANSFER:
                controller.nextState(SingletonScreen.TransferScreen());
                break;
            case TRANSACTION_HISTORY:
                controller.nextState(SingletonScreen.TransactionHistoryScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}
