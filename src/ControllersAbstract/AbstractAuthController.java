package ControllersAbstract;

import Exceptions.IncorrectPassword;
import Exceptions.UserDoesntExist;
import Exceptions.UserExists;
import Services.AuthService;
import Utils.AppState;
import ViewsAbstract.IAuthView;

public abstract class AbstractAuthController {
    IAuthView view;
    private AuthService authService = AuthService.getInstance();
    AppState state = AppState.getInstance();

    public AbstractAuthController(IAuthView view) {
        this.view = view;
    };

    public void login(String username, String password) {
        try {
            authService.login(username, password);
            view.authSuccessful();
        }
        catch (UserDoesntExist e1) {
            // Show it in view
            view.authError(e1);
        }
        catch (IncorrectPassword e2) {
            // Do the same
            view.authError(e2);
        }
    }

    public void register(String username, String password){
        try {
            authService.register(username, password);
            view.authSuccessful();
        }
        catch ( UserExists e) {
            // Show in view
            view.authSuccessful();
        }
    }
}
