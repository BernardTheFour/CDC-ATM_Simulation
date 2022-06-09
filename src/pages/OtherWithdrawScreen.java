package pages;

import java.io.IOException;

import pattern.IState;
import pattern.Singleton;
import pattern.StateController;

public class OtherWithdrawScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        super.controller = controller;
    }

    @Override
    public void logic() {
        System.out.println("\n--Other Withdraw--");
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

       int balance = Singleton.getLoggedUser().getBalance();

       if (balance < withdraw){
           System.out.println("Insufficient balance to withdraw $" + withdraw);
           return;
       }

       balance -= withdraw;

       Singleton.getLoggedUser().setBalance(balance);
       Singleton.setWithdraw(balance);
       super.nextPage = Pages.SUMMARY;
    }

    @Override
    public void navigate() {
        switch (super.nextPage) {
            case SUMMARY:
                controller.nextState(Singleton.SummaryScreen());
                break;
            default:
                controller.nextState(controller.getCurrentState());
                break;
        }
        super.nextPage = Pages.DEFAULT;
    }
}
