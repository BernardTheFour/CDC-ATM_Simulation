package pattern;

public interface IState {
    public void init(StateController controller);
    public void logic();
    public void navigate();
}
