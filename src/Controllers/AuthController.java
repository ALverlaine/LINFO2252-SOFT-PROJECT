package Controllers;
import Exceptions.IncorrectPassword;
import Exceptions.UserDoesntExist;
import Exceptions.UserExists;
import Models.User;
import Services.AuthService;
import Views.AuthView;
import Utils.AppState;

public class AuthController extends AbstractController {
    private AuthView view;
    private AuthService authService = AuthService.getInstance();
    AppState state = AppState.getInstance();

    public AuthController(AuthView view) {
        this.view = view;
    }

    public void parseAuthInput(int input) {
        final int LOGIN = 1;
        final int REGISTER = 2;
        final int EXIT = 3;

        switch (input) {
            case LOGIN -> view.login();
            case REGISTER -> view.register();
            case EXIT -> view.exit();
            default -> view.inputNotRecognized();
        }
    }

    public void reset() {
        state.setConnectedUser(null);
    }

    public void login(String username, String password) {
        try {
            authService.login(username, password);
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
            authService.register(username, password);
            view.authSuccessful();
        }
        catch ( UserExists e) {
            // Show in view
            view.registerUnsuccessful();
        }
    }
}

