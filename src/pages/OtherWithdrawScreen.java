package pages;

import pattern.IStatePattern;
import pattern.StateController;

public class OtherWithdrawScreen implements IStatePattern{

    @Override
    public void show(StateController controller) {
        System.out.println("This is other withdraw screen");
        
    }
    
}
