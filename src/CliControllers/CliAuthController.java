package CliControllers;
import ControllersAbstract.AbstractAuthController;
import Services.AuthService;
import Views.AuthCLIView;
import Utils.AppState;
import ViewsAbstract.IAuthView;

public class CliAuthController extends AbstractAuthController {
    private AuthCLIView view;
    private AuthService authService = AuthService.getInstance();
    AppState state = AppState.getInstance();

    public CliAuthController(IAuthView view) {
        super(view);
        this.view = (AuthCLIView) view;
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

}

