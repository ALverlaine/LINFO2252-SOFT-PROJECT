package State.UI;

import State.Controllers.AbstractController;
import State.State;

import java.util.HashMap;

public enum UI {
    INSTANCE;
    public final CLI cli = new CLI();

    public void CLIsetup(State state) {
        HashMap<String, AbstractController> controllers = state.getCommand();
        cli.show(state, controllers);
    }
}
