import State.Exceptions.NoUserConnected;
import State.Models.User;
import State.State;
import State.Views.AuthView;

public class main {
    public static void main(String[] args) throws NoUserConnected {
        AuthView view = new AuthView();
        view.run();
    }
}
