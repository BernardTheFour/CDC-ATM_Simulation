package pattern;

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
        currentState.init(this);
        currentState.logic();
        currentState.navigate();
    }

    public StateController (IState initState){
        currentState = initState;        
    }
}
