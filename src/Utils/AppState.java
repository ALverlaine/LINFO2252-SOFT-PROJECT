package Utils;

import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.*;
import Models.User;

import java.util.*;

public class AppState {

    private static AppState instance = null;
    private Map<String, User> users = new HashMap<>();
    public User connectedUser = null;


    private AppState() {
    }

    public static AppState getInstance() {
        if (instance == null) instance = new AppState();
        return instance;
    }


    public TreeMap<FeatureName, Feature> getFeatures() {
        Map<FeatureName, Feature> userFeatures = new LinkedHashMap<FeatureName, Feature>();
        userFeatures.put(FeatureName.Status, new Status());
        userFeatures.put(FeatureName.Research, new Research());
        userFeatures.put(FeatureName.Driving, new Driving());
        userFeatures.put(FeatureName.Link_Protection, new Link());
        return new TreeMap<>(userFeatures);
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
        for (Map.Entry<String, User> user: users.entrySet()) {
            System.out.println(users.get("kim") == null);
            System.out.println(user.getKey());
        }
        return users.get(username) != null;
    }

    public void setConnectedUser(User user) {
        connectedUser = user;
    }

    public void addUser(User user) {
        users.put(user.getName(), user);
    }
    //------------------------------------------------------------------------------------------------------------------


}
