package pages;

import pattern.IStatePattern;
import pattern.StateController;

public class TransferScreen extends Page implements IStatePattern {

    @Override
    public void init(StateController controller) {
        this.controller = controller;
        nextPage = Pages.TRANSFER;
    }

    @Override
    public void show() {
        System.out.println("This is fund transfer screen");
    }

    @Override
    public void navigate() {
        // TODO Auto-generated method stub
    }
}
