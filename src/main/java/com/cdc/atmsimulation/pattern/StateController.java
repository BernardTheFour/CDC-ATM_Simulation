package com.cdc.atmsimulation.pattern;

import com.cdc.atmsimulation.interfaces.IState;

public class StateController {
    private IState currentState;
    
    public IState getCurrentState(){
        return currentState;
    }

    public void nextState(IState state){
        currentState = state;
        run();
    }

    public void run(){ 
        currentState.init();    
        currentState.logic();
        currentState.navigate();
    }

    public void firstState(IState initState){
        currentState = initState;   
    }
}
