package State;

import State.Controllers.AbstractController;
import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;
import State.Features.Feature;
import State.Features.FeatureName;
import State.Features.Research;
import State.Features.Status;
import State.Models.User;

import java.util.*;

public class State {

    private static State instance = null;
    private Map<String, User> users = new HashMap<>();
    public User connectedUser;
    private HashMap<String, AbstractController> commands = new HashMap<>();
    private AbstractController command;
    private String inputCMD;
    private String inputArgs;


    private Map<FeatureName, Feature> features;

    private State() {
        createFeatures();
    }

    public static State getInstance() {
        if (instance == null) instance = new State();
        return instance;
    }

    private void createFeatures() {
        features = new LinkedHashMap<FeatureName, Feature>();
        features.put(FeatureName.Status, new Status());
        features.put(FeatureName.Research, new Research());
    }

    public TreeMap<FeatureName, Feature> getFeatures() {

        Map<FeatureName, Feature> userFeatures = new LinkedHashMap<FeatureName, Feature>();
        features.put(FeatureName.Status, new Status());
        features.put(FeatureName.Research, new Research());
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
        return users.get(username) != null;
    }

    public void setConnectedUser(User user) {
        connectedUser = user;
    }

    public void addUser(User user) {
        users.put(user.getName(), user);
    }
    //------------------------------------------------------------------------------------------------------------------
    public HashMap<String, AbstractController> getCommand(){
        return commands;
    }
    public void setCommand(HashMap<String, AbstractController> commands){
        this.commands = commands;
    }

    public void setCMD(String[] command){
        this.inputCMD = command[0];
        this.inputArgs = command.length > 1 ? command[1] : null;
    }

    public void setOneCommand(AbstractController command){
        this.command = command;
    }

    public AbstractController getOneCommand(){
        return this.command;
    }

    public String getInputCMD(){
        return this.inputCMD;
    }


}
