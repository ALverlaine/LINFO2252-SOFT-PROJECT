import State.Exceptions.NoUserConnected;
import State.Models.User;
import State.State;

public class main {
    public static void main(String[] args) throws NoUserConnected {
        State state = new State();

        while(state.connectedUser != null){
            state.getCommand();
        }
    }
}
