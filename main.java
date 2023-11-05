import State.Exceptions.AlreadyHasChat;
import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;
import State.Models.Chat;
import State.Models.User;
import State.Services.Database;
import State.State;
import State.Views.AuthView;

public class main {
    public static void main(String[] args) throws NoUserConnected, UserDoesntExist, AlreadyHasChat {
        State state = State.getInstance();
        Database.populateUsersDB(state);
        Database.populateChatsDB(state);
        AuthView view = new AuthView();
        view.run();
    }
}
