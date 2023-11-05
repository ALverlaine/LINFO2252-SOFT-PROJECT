package State.Controllers;
import State.Exceptions.IncorrectPassword;
import State.Exceptions.UserDoesntExist;
import State.Exceptions.UserExists;
import State.Models.User;
import State.Services.AuthService;
import State.Views.AuthView;

public class AuthController extends AbstractController {
    private AuthView view;
    private AuthService authService = AuthService.getInstance();

    public AuthController(AuthView view) {
        this.view = view;
    }

    public void login(String username, String password) {
        try {
            User user = authService.login(username, password);
            state.addUser(user);
            state.setConnectedUser(user);
            view.authSuccessful();
        }
        catch (UserDoesntExist e1) {
            // Show it in view
            view.loginUnsuccessful(e1);
        }
        catch (IncorrectPassword e2) {
            // Do the same
            view.loginUnsuccessful(e2);
        }
    }

    public void register(String username, String password){
        try {
            User user = authService.register(username, password);
            state.addUser(user);
            state.setConnectedUser(user);
        }
        catch ( UserExists e) {
            // Show in view
            view.registerUnsuccessful();
        }
    }
}

