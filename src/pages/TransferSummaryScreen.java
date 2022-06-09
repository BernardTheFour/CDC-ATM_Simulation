package pages;

import pattern.IState;
import pattern.StateController;

public class TransferSummaryScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        super.controller = controller;
        super.nextPage = Pages.DEFAULT;
    }

    @Override
    public void logic() {
        System.out.println("This is fund transfer summary screen");
    }

    @Override
    public void navigate() {
        // TODO Auto-generated method stub
    }
}
