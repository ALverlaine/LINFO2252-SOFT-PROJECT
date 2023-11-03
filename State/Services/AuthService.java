package State.Services;

import State.Exceptions.IncorrectPassword;
import State.Exceptions.UserDoesntExist;
import State.Exceptions.UserExists;
import State.Models.User;

import java.util.Map;
import java.util.Objects;

import State.State;

public class AuthService {

    private static AuthService instance;

    private AuthService() {}

    public static AuthService getInstance() {
        if (instance == null) instance = new AuthService();
        return instance;
    }

    private final State state = State.getInstance();

    public User login(String username, String password) throws UserDoesntExist, IncorrectPassword {
        User user = state.getUser(username);
        if (Objects.equals(password, user.password)) return user;
        else throw new IncorrectPassword();
    }

    public User register(String username, String password) throws UserExists {
        if (state.userExists(username)) throw new UserExists();
        User user = new User(username, password);
        state.addUser(user);
        return user;
    }

}
