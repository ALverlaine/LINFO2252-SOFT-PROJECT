package State.Views;

import State.Controllers.RegisterController;
import State.Exceptions.PasswordDoesntMatch;
import State.Exceptions.UserExists;

import java.util.HashMap;

public class RegisterView extends AbstractView {

    private final RegisterController controller;

    public RegisterView(HashMap<String, Object> params) {
        super(params);
        controller = new RegisterController(this);
    }

    @Override
    public void initialize() {

    }

    public void register() {
        //Get username and password from cli
        String username = "a";
        String password = "b";
        String confirmPassword = "b";
        if (password.equals(confirmPassword)) controller.register(username, password);
        else registerUnsuccessful(new PasswordDoesntMatch());
    }

    public void registerUnsuccessful(PasswordDoesntMatch e) {
        //Show error in cli
    }

    public void registerUnsuccessful(UserExists e) {
        //Show error in cli
    }
}
