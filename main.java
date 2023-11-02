import State.State;

public class main {
    public static void main(String[] args) {
        State state = new State();
        if(!state.isConnected()){
            state.connect();
        }
        while(state.isRunning()){
            state.getCommand();
        }
    }
}
