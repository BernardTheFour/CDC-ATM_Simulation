package pages;

import pattern.IStatePattern;
import pattern.StateController;

public class SummaryScreen implements IStatePattern{

    @Override
    public void show(StateController controller) {
        System.out.println("This is summary screen");
        
    }
    
}
