package pages;

import pattern.IState;
import pattern.StateController;

public class WithdrawScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        this.controller = controller;
        nextPage = Pages.TRANSFER;
    }

    @Override
    public void run() {
        System.out.println("This is withdraw screen");
    }

    @Override
    public void navigate() {
        // TODO Auto-generated method stub
    }
}
