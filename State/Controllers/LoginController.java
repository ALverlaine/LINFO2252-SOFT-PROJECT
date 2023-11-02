package State.Controllers;
import State.Exceptions.IncorrectPassword;
import State.Exceptions.UserDoesntExist;
import State.Models.User;
import State.Services.AuthService;
import State.State;
import State.Views.LoginView;

public class LoginController extends AbstractController {
    private State state;
    private LoginView view;
    private AuthService authService = AuthService.getInstance();

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void login(String username, String password) {
        try {
            User user = authService.login(username, password);
            state.addUser(user);
            state.setConnectedUser(user);
        }
        catch (UserDoesntExist e1) {
            // Show it in view
        }
        catch (IncorrectPassword e2) {
            // Do the same
        }
    }
}

