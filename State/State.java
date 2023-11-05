package State;

import State.Commands.Command;
import State.Commands.CommandsDirector;
import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;
import State.Models.Chat;
import State.Models.User;
import State.Services.AuthService;
import State.Services.ChatService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class State {

    private static State instance = null;
    private Map<String, User> users;
    private Map<String, List<Map<String, Chat>>> chats;
    public User connectedUser;
    CommandsDirector commands;

    private State() {
        ChatService chatService = ChatService.getInstance();
        AuthService authService = AuthService.getInstance();
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

    public String getConnectedUserName() throws NoUserConnected {
        if (connectedUser != null) return connectedUser.getName();
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
        users.put(user.getName(), user);
    }

    public Command getCommand(){
        return commands;
    }

}
