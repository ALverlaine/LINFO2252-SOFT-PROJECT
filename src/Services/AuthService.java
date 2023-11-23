package Services;

import Exceptions.IncorrectPassword;
import Exceptions.UserDoesntExist;
import Exceptions.UserExists;
import Models.User;

import java.util.Objects;

import Utils.AppState;

public class AuthService {

    private static AuthService instance;

    private AuthService() {}

    public static AuthService getInstance() {
        if (instance == null) instance = new AuthService();
        return instance;
    }

    private final AppState appState = AppState.getInstance();

    public User login(String username, String password) throws UserDoesntExist, IncorrectPassword {
        User user = appState.getUser(username);
        if (Objects.equals(password, user.getPassword())) return user;
        else throw new IncorrectPassword();
    }

    public User register(String username, String password) throws UserExists {
        if (appState.userExists(username)) throw new UserExists();
        User user = new User(username, password);
        appState.addUser(user);
        return user;
    }

}
