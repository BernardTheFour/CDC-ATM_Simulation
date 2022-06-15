package pages;

import pattern.IState;
import pattern.SingletonScreen;
import pattern.StateController;

public class TransactionScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        System.out.println("\n-------------------------------");
        super.controller = controller;
        super.nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {
        System.out.println("--Transactions--");
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");

        System.out.print("\nChoose option: ");

        String answer = super.input.nextLine();

        switch (answer) {
            case "1":
                super.nextPage = Pages.WITHDRAW;
                break;
            case "2":
                super.nextPage = Pages.TRANSFER;
                break;
            case "3":
                super.nextPage = Pages.WELCOME;
                break;
        }
    }

    @Override
    public void navigate() {
        switch (super.nextPage) {
            case WELCOME:
                controller.nextState(SingletonScreen.WelcomeScreen());
                break;
            case WITHDRAW:
                controller.nextState(SingletonScreen.WithdrawScreen());
                break;
            case TRANSFER:
                controller.nextState(SingletonScreen.TransferScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}
