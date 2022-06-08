package pages;

import pattern.IStatePattern;
import pattern.StateController;

public class WithdrawScreen implements IStatePattern {

    @Override
    public void show(StateController controller) {
        System.out.println("This is withdraw screen");        
    }    
}
