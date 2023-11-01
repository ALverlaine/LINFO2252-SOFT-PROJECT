package State.Commands;

import State.Command;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class CommandsDirector extends Command {
    private HashMap<String, Command> commands = new HashMap<String, Command>();
    public CommandsDirector(){
    }

    public void addCommand(String name, Command command){
        if(!(commands.containsKey(command))){
            commands.put(name, command);
        }
    }
    public abstract void addAllCommand();


}
