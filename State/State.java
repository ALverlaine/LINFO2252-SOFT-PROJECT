package State;

import State.Commands.Command;
import State.Commands.CommandsDirector;
import State.Users.User.User;
import State.Users.Users;

import java.util.Objects;

public class State {
    String currState;
    User currUser;
    Users users;
    String currStateConnexion = "Not connected";
    CommandsDirector commands;

    public State() {
        this.currState = "Running";
    }

    public boolean isRunning(){
        return Objects.equals(this.currState, "Running");
    }

    public boolean isConnected(){
        return Objects.equals(this.currStateConnexion, "Connected");
    }

    public void connect(User user){
        if(user)
    }

    public void setExit(){
        this.currState = "Exit";
    }


    public Command getCommand(){
        return commands;
    }



}
