import State.State;

public class main {
    public static void main(String[] args) {
        State state = new State();
        while(!(state.isRunning())){
            state.getCommand();
        }
    }
}
