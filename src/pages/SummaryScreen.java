package pages;

import pattern.IState;
import pattern.StateController;

public class SummaryScreen extends Page implements IState{

    @Override
    public void init(StateController controller) {
        this.controller = controller;
        nextPage = Pages.SUMMARY;
    }

    @Override
    public void logic() {
        System.out.println("This is summary screen");        
    }

    @Override
    public void navigate() {
        // TODO Auto-generated method stub        
    }    
}
