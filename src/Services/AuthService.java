package Services;

import Exceptions.IncorrectPassword;
import Exceptions.UserDoesntExist;
import Exceptions.UserExists;
import Models.User;

import java.util.Objects;

import Utils.AppState;

public class AuthService {

    private static AuthService instance;
    private AppState appState = AppState.getInstance();
    private AuthService() {}

    public static AuthService getInstance() {
        if (instance == null) instance = new AuthService();
        return instance;
    }

    public void login(String username, String password) throws UserDoesntExist, IncorrectPassword {
        User user = appState.getUser(username);
        if (Objects.equals(password, user.getPassword()))
        {
            appState.addUser(user);
            appState.setConnectedUser(user);
        }
        else throw new IncorrectPassword();
    }

    public void register(String username, String password) throws UserExists {
        if (appState.userExists(username)) throw new UserExists();
        User user = new User(username, password);
        appState.addUser(user);
        appState.setConnectedUser(user);
    }

}
