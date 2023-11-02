package State;

import State.Commands.CommandsDirector;
import State.Users.User.User;
import State.Users.Users;

import java.util.ArrayList;
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
        if(Objects.equals(this.currStateConnexion, "Not connected")){
            if(users.exists(user)){
                this.currUser = user;
                this.currStateConnexion = "Connected";
            }else{
                User NewUser = new User(user.name, user.password);
                users.users.add(NewUser);
            }

        }
    }

    public void setExit(){
        this.currState = "Exit";
    }


    public Command getCommand(){
        return commands;
    }



}
