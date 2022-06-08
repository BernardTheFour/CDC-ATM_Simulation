package pattern;

public class StateController {
    private IStatePattern currentState;
    
    public IStatePattern getCurrentState(){
        return currentState;
    }

    public void nextState(IStatePattern state){
        currentState = state;
        run();
    }

    public void run(){
        currentState.init(this);
        currentState.show();
        currentState.navigate();
    }

    public StateController (IStatePattern initState){
        currentState = initState;        
    }
}
