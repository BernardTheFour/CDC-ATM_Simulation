package pages;

import pattern.IStatePattern;
import pattern.StateController;

public class TransferScreen implements IStatePattern {

    @Override
    public void show(StateController controller) {
       System.out.println("This is fund transfer screen");        
    }
    
}
