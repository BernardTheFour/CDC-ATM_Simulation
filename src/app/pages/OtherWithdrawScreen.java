package app.pages;

import app.pattern.IState;
import app.pattern.SingletonData;
import app.pattern.SingletonScreen;
import app.pattern.StateController;

public class OtherWithdrawScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        System.out.println("\n-------------------------------");
        super.controller = controller;
        super.nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {
        System.out.println("--Other Withdraw--");
        System.out.println("Enter amount to withdraw (max $1000):");
        System.out.printf("$");

        String answer = super.input.nextLine();

       if (!answer.matches("[0-9]+")){
           System.out.println("Invalid amount: please input only number");
           return;
       }

       int withdraw = Integer.parseInt(answer);

       if (withdraw % 10 != 0){
           System.out.println("Invalid amount: can only withdraw multiple of 10");
           return;
       }

       int balance = SingletonData.getLoggedUser().getBalance();

       if (balance < withdraw){
           System.out.println("Insufficient balance to withdraw $" + withdraw);
           return;
       }

       balance -= withdraw;

       SingletonScreen.SummaryScreen().setInfo(withdraw);
       super.nextPage = Pages.SUMMARY;
    }

    @Override
    public void navigate() {
        switch (super.nextPage) {
            case SUMMARY:
                controller.nextState(SingletonScreen.SummaryScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
    }
}