package GUI;

import Exceptions.IncorrectPassword;
import Exceptions.UserDoesntExist;
import GuiControllers.GuiAuthController;
import GuiInterfaces.Views.IAppViewController;
import ViewsAbstract.IAuthView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;

public class GuiRegisterViewController extends AbstractViewController implements IAuthView {

    @FXML private TextField usernameSignup;
    @FXML private PasswordField createPasswordSignup;
    @FXML private PasswordField confirmPasswordSignup;
    GuiAuthController controller;
    IAppViewController app;

    public GuiRegisterViewController(IAppViewController app) {
        super(app);
        this.app = app;
        this.controller = new GuiAuthController(this);
    }

    @FXML
    public void goToLogin() {
        switchView(EPages.SIGN_IN);
    }

    @FXML
    public void signUp() {
        String username = usernameSignup.getText();
        String password = createPasswordSignup.getText();
        String confirmPassword = confirmPasswordSignup.getText();
        if (Objects.equals(password, confirmPassword)) controller.register(username, confirmPassword);
    }

    @Override
    public void authSuccessful() {
        app.handleViewChange(EPages.SIGN_IN);
    }

    @Override
    public void authError(Exception e) {
        showAlert(Alert.AlertType.ERROR, "There was an error registering you", "We couldn't sign you up", "");
    }

}
