import State.Exceptions.NoUserConnected;
import State.Models.User;
import State.State;
import State.Views.AuthView;

public class main {
    public static void main(String[] args) throws NoUserConnected {
        User a = new User("a", "a");
        User b = new User("b", "b");
        User c = new User("c", "c");
        State state = State.getInstance();
        state.addUser(a);
        state.addUser(b);
        state.addUser(c);

        AuthView view = new AuthView();
        view.run();
    }
}
