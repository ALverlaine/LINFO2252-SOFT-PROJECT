package State.Controllers;

import State.Exceptions.IncorrectPassword;
import State.Exceptions.UserDoesntExist;
import State.Exceptions.UserExists;
import State.Models.User;
import State.Services.AuthService;
import State.State;
import State.Views.RegisterView;

public class RegisterController extends AbstractController{
    private RegisterView view;
    private AuthService authService = AuthService.getInstance();

    public RegisterController(RegisterView view) {
        this.view = view;
    }

    public void register(String username, String password){
        try {
            User user = authService.register(username, password);
            state.addUser(user);
            state.setConnectedUser(user);
        }
        catch ( UserExists e) {
            // Show in view
            view.registerUnsuccessful(e);
        }
    }
}
