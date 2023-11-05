package State.Views;

import State.Controllers.LoginController;
import State.Exceptions.IncorrectPassword;
import State.Exceptions.UserDoesntExist;

import java.util.HashMap;

public class LoginView extends AbstractView {

    private final LoginController controller;
    public LoginView() {
        super();
        controller = new LoginController(this);
    }

    @Override
    public void initialize() {

    }

    public void login() {
        String username = "a";
        String password = "b";

        //Get them from input
        controller.login(username, password);
    }

    public void loginSuccessful() {
        // Go to homepage
    }

    public void loginUnsuccessful(IncorrectPassword e) {
        //Show error message
    }

    public void loginUnsuccessful(UserDoesntExist e) {
        //Show error message
    }


}

