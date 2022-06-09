package pattern;

public interface IState {
    public void init(StateController controller);
    public void run();
    public void navigate();
}
