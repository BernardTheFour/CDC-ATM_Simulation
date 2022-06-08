package pages;

import pattern.IStatePattern;
import pattern.StateController;

public class SummaryScreen extends Page implements IStatePattern{

    @Override
    public void init(StateController controller) {
        this.controller = controller;
        nextPage = Pages.SUMMARY;
    }

    @Override
    public void show() {
        System.out.println("This is summary screen");        
    }

    @Override
    public void navigate() {
        // TODO Auto-generated method stub        
    }    
}
