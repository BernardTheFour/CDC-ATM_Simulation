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
        currentState.show();
        currentState.navigate();
    }

    public StateController (IState initState){
        currentState = initState;        
    }
}
