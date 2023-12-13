package GUI;

import GUI.EPages;
import GuiInterfaces.Views.IAppViewController;
import GuiInterfaces.Views.IViewController;
import Patterns.AlertFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class AbstractViewController implements IViewController {

    protected IAppViewController app;
    protected AlertFactory alertFactory;

    public AbstractViewController(IAppViewController app) {
        this.app = app;
        alertFactory = new AlertFactory();
    }

    public void switchView(EPages page) {
        app.handleViewChange(page);
    }

    public void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = alertFactory.makeAlert(type, title, header, content);
        alert.show();
    }

}
