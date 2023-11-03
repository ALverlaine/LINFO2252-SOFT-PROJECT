package State;

import State.Commands.Command;
import State.Commands.CommandsDirector;
import State.Exceptions.AlreadyConnected;
import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;
import State.Models.User;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class State {

    private static State instance = null;
    private Map<String, User> users;
    public User connectedUser;
    CommandsDirector commands;

    public State() {
    }

    public static State getInstance() {
        if (instance == null) instance = new State();
        return instance;
    }

    public Map<String, User> getUsers() {
        return Collections.unmodifiableMap(users);
    }

    public User getUser(String username) throws UserDoesntExist {
        User user = users.get(username);
        if (user == null) throw new UserDoesntExist();
        return user;
    }


    public User getConnectedUser() throws NoUserConnected {
        if (connectedUser != null) return connectedUser;
        else throw new NoUserConnected();
    }

    public boolean isConnected(){
        return connectedUser != null;
    }

    public boolean userExists(String username) {
        return users.get(username) != null;
    }

    public void setConnectedUser(User user) {
        connectedUser = user;
    }

    public void addUser(User user) {
        users.put(user.name, user);
    }

    public Command getCommand(){
        return commands;
    }

}
