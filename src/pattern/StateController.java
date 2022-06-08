package pattern;

public class StateController {
    private IStatePattern currentState;
    
    public IStatePattern getCurrentState(){
        return currentState;
    }

    public void nextState(IStatePattern state){
        currentState = state;
        showCurrent();
    }

    public void showCurrent(){
        currentState.show(this);
    }

    public StateController (IStatePattern initState){
        currentState = initState;
    }
}
