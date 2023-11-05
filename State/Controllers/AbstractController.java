package State.Controllers;

import State.State;

import java.util.HashMap;

public class AbstractController {

    State state = State.getInstance();
    private final HashMap<String, AbstractController> methods;
    public AbstractController() {

        this.methods = new HashMap<>();
        this.addAllCommand();
    }

    public AbstractController ApplyNewCommand(State state) {
        AbstractController nextRunner = methods.get(state.getInputCMD());
        if (nextRunner != null) {
            return nextRunner;
        }
        throw new IllegalStateException();
    }

    public void addAllCommand(){

    }
}
