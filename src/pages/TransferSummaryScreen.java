package pages;

import pattern.IStatePattern;
import pattern.StateController;

public class TransferSummaryScreen extends Page implements IStatePattern {

    @Override
    public void init(StateController controller) {
        this.controller = controller;
        nextPage = Pages.TRANSFER;
    }

    @Override
    public void show() {
        System.out.println("This is fund transfer summary screen");
    }

    @Override
    public void navigate() {
        // TODO Auto-generated method stub
    }
}
