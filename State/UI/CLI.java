package State.UI;

import State.Controllers.AbstractController;
import State.State;

import java.util.HashMap;
import java.util.Scanner;

public class CLI {
    public void show(State state, HashMap<String, AbstractController> controllers){
        //System.out.println("Welcome to the chat app!");
        //System.out.println("Please enter your username:");
        this.displayPossibleCmd(controllers);
        //take input
        state.setCMD(CLI.getArgs());
        //set new state  of command
        state.setOneCommand(state.getOneCommand().ApplyNewCommand(state));
    }

    public void displayPossibleCmd(HashMap<String, AbstractController> controllers) {
        System.out.println("Possible commands:");
        for (String cmd : controllers.keySet()) {
            System.out.println(cmd);
        }
        sep();
        System.out.println("Please enter your command:");
    }
    public void sep() {
        StringBuilder dashes = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            dashes.append('-');
        }
        System.out.println(dashes.toString());
    }

    public static String[] getArgs() { return Input.getInputs().split(" "); }

}
