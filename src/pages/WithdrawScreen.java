package pages;

import pattern.IState;
import pattern.SingletonData;
import pattern.SingletonScreen;
import pattern.StateController;

public class WithdrawScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        System.out.println("\n-------------------------------");
        super.controller = controller;
        super.nextPage = Pages.DEFAULT;
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

        int balance = SingletonData.getLoggedUser().getBalance();

        if (balance < withdraw) {
            System.out.println("Insufficient balance to withdraw $" + withdraw);
            return;
        }

        balance -= withdraw;
        
        SingletonData.getLoggedUser().setBalance(balance);
        SingletonScreen.SummaryScreen().setInfo(withdraw);
    }

    @Override
    public void navigate() {
        switch (super.nextPage) {
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
