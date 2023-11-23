package Controllers;

import Utils.AppState;

import java.util.HashMap;

public class AbstractController {

    AppState appState = AppState.getInstance();
    private final HashMap<String, AbstractController> methods;
    public AbstractController() {

        this.methods = new HashMap<>();
        this.addAllCommand();
    }

    public void addAllCommand(){

    }
}
