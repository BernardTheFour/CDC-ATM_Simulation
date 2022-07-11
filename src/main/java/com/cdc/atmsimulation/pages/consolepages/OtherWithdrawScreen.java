package com.cdc.atmsimulation.pages.consolepages;

import com.cdc.atmsimulation.interfaces.IState;
import com.cdc.atmsimulation.pattern.StateController;
import com.cdc.atmsimulation.pattern.singletons.SingletonScreen;

public class OtherWithdrawScreen extends Page implements IState {

    public OtherWithdrawScreen(StateController controller) {
        super(controller);
    }

    @Override
    public void init() {
        System.out.println("\n-------------------------------");
        nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {
        System.out.println("--Other Withdraw--");
        System.out.println("Enter amount to withdraw (max $1000):");
        System.out.printf("$");

        String answer = cmdInput.nextLine();

        if (!answer.matches("[0-9]+")) {
            System.out.println("Invalid amount: please input only number");
            return;
        }

        int withdraw = Integer.parseInt(answer);

        if (withdraw % 10 != 0) {
            System.out.println("Invalid amount: can only withdraw multiple of 10");
            return;
        }

        int balance = loggedAccount.getBalance();

        if (balance < withdraw) {
            System.out.println("Insufficient balance to withdraw $" + withdraw);
            return;
        }

        SingletonScreen.SummaryScreen().setInfo(withdraw);
        nextPage = Pages.SUMMARY;
    }

    @Override
    public void navigate() {
        switch (nextPage) {
            case SUMMARY:
                controller.nextState(SingletonScreen.SummaryScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}
