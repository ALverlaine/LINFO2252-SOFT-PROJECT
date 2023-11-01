package State;

import State.Commands.CommandsDirector;

import java.util.ArrayList;

public class State {
    String curr;
    CommandsDirector commands;
    public State(){
        this.curr = "Running";

    }
    public void print(){
        System.out.println("State: " + this.curr);
    }
    public State getState(){
        return this;
    }
    public boolean isRunning(){
        if(this.curr == "Running"){
            return true;
        }
        else{
            return false;
        }
    }
    public void setExit(){
        this.curr = "Exit";
    }
    public Command getCommand(){
        return commands;
    }

}
