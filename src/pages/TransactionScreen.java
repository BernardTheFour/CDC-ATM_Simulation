package pages;

import pattern.IState;
import pattern.Singleton;
import pattern.StateController;

public class TransactionScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        this.controller = controller;
    }

    @Override
    public void logic() {
        System.out.println("\n--Transactions--");
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");

        String answer = input.nextLine();

        switch (answer) {
            case "1":
                nextPage = Pages.WITHDRAW;
                break;
            case "2":
                nextPage = Pages.TRANSFER;
                break;
            case "3":
                nextPage = Pages.WELCOME;
                break;
        }
    }

    @Override
    public void navigate() {
        switch (nextPage) {
            case WELCOME:
                controller.nextState(Singleton.WelcomeScreen());
                break;
            case WITHDRAW:
                controller.nextState(Singleton.WithdrawScreen());
                break;
            case TRANSFER:
                controller.nextState(Singleton.TransferScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}
