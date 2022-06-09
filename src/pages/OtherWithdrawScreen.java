package pages;

import pattern.IState;
import pattern.StateController;

public class OtherWithdrawScreen extends Page implements IState{

    @Override
    public void init(StateController controller) {
        this.controller = controller;
        nextPage = Pages.OTHER_WITHDRAW;        
    }

    @Override
    public void show() {
        System.out.println("This is other withdraw screen");        
    }

    @Override
    public void navigate() {
        // TODO Auto-generated method stub        
    }    
}
