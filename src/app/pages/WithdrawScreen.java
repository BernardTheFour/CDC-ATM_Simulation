package app.pages;

import app.pattern.IState;
import app.pattern.SingletonScreen;
import app.pattern.StateController;

public class WithdrawScreen extends Page implements IState {

    public WithdrawScreen(StateController controller) {
        super(controller);
    }

    @Override
    public void init() {
        System.out.println("\n-------------------------------");
        nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {
        System.out.println("--Withdraw--");
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");

        System.out.print("\nNavigate to: ");

        String answer = cmdInput.nextLine();
        int withdraw = 0;

        switch (answer) {
            case "1":
                nextPage = Pages.SUMMARY;
                withdraw = 10;
                break;
            case "2":
                nextPage = Pages.SUMMARY;
                withdraw = 50;
                break;
            case "3":
                nextPage = Pages.SUMMARY;
                withdraw = 100;
                break;
            case "4":
                nextPage = Pages.OTHER_WITHDRAW;
                return;
            case "5":
                nextPage = Pages.TRANSACTION;
                return;
        }

        int balance = loggedAccount.getBalance();

        if (balance < withdraw) {
            System.out.println("Insufficient balance to withdraw $" + withdraw);
            return;
        }

        SingletonScreen.SummaryScreen().setInfo(withdraw);
    }

    @Override
    public void navigate() {
        switch (nextPage) {
            case SUMMARY:
                controller.nextState(SingletonScreen.SummaryScreen());
                break;
            case OTHER_WITHDRAW:
                controller.nextState(SingletonScreen.OtherWithdrawScreen());
                break;
            case TRANSACTION:
                controller.nextState(SingletonScreen.TransactionScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}
