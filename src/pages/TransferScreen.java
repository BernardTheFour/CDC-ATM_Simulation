package pages;

import pattern.IState;
import pattern.StateController;

public class TransferScreen extends Page implements IState {

    @Override
    public void init(StateController controller) {
        this.controller = controller;
        nextPage = Pages.TRANSFER;
    }

    @Override
    public void logic() {
        System.out.println("This is fund transfer screen");
    }

    @Override
    public void navigate() {
        // TODO Auto-generated method stub
    }
}
