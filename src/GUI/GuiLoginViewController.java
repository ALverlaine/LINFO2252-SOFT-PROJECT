package GUI;

import GuiControllers.GuiAuthController;
import Exceptions.IncorrectPassword;
import Exceptions.UserDoesntExist;
import GuiInterfaces.Views.ILoginViewController;
import ViewsAbstract.IAuthView;
import GuiInterfaces.Views.IAppViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiLoginViewController extends AbstractViewController implements Initializable, ILoginViewController, IAuthView {

    @FXML
    private PasswordField passwordLogin;
    @FXML private TextField usernameLogin;
    GuiAuthController controller;
    IAppViewController app;

    public GuiLoginViewController(IAppViewController app){
        super(app);
        this.app = app;
        this.controller = new GuiAuthController(this);
    }
    
    public void goToSignUp(MouseEvent mouseEvent) {
        switchView(EPages.SIGN_UP);
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        String username = usernameLogin.getText();
        String password = passwordLogin.getText();
        controller.login(username, password);
    }

    @Override
    public void authSuccessful() {
        clearForm();
        switchView(EPages.CHAT_LIST);
    }

    @Override
    public void authError(Exception e) {
        showAlert(Alert.AlertType.ERROR, "There was an error logging you in", "We couldn't log you in", "");
    }

    @Override
    public void clearForm() {
        usernameLogin.clear();
        passwordLogin.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
