import State.Exceptions.NoUserConnected;
import State.Models.User;
import State.State;

public class main {
    public static void main(String[] args) throws NoUserConnected {
        State state = new State();
        if(!state.isConnected()){
            try {
                User user = state.getConnectedUser();
            } catch (Exception e) {
                User user = new User("user", "password");
            }

        }
        while(state.connectedUser != null){
            state.getCommand();
        }
    }
}
