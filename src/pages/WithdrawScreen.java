package pages;

import pattern.IState;
import pattern.Singleton;
import pattern.StateController;

public class WithdrawScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        super.controller = controller;
    }

    @Override
    public void logic() {
        System.out.println("\n--Withdraw--");
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Back");

        System.out.print("\nNavigate to: ");

        String answer = super.input.nextLine();
        int withdraw = 0;

        switch (answer) {
            case "1":
                super.nextPage = Pages.SUMMARY;
                withdraw = 10;
                break;
            case "2":
                super.nextPage = Pages.SUMMARY;
                withdraw = 50;
                break;
            case "3":
                super.nextPage = Pages.SUMMARY;
                withdraw = 100;
                break;
            case "4":
                super.nextPage = Pages.OTHER_WITHDRAW;
                return;
            case "5":
                super.nextPage = Pages.TRANSACTION;
                return;
        }

        int balance = Singleton.getLoggedUser().getBalance();

        if (balance < withdraw) {
            System.out.println("Insufficient balance to withdraw $" + withdraw);
            return;
        }

        balance -= withdraw;

        Singleton.getLoggedUser().setBalance(balance);
        Singleton.setWithdraw(balance);
    }

    @Override
    public void navigate() {
        switch (super.nextPage) {
            case SUMMARY:
                controller.nextState(Singleton.SummaryScreen());
                break;
            case OTHER_WITHDRAW:
                controller.nextState(Singleton.OtherWithdrawScreen());
                break;
            case TRANSACTION:
                controller.nextState(Singleton.TransactionScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
        super.nextPage = Pages.DEFAULT;
    }
}
