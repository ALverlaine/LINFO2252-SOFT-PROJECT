package State.Views;

import State.Controllers.AbstractController;

import java.util.HashMap;

public abstract class AbstractView {
    public AbstractView(HashMap<String, Object> params) {}
    public abstract void initialize();
}
