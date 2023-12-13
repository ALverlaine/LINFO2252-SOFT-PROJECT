package GuiInterfaces.Views;

import GUI.EPages;
import javafx.scene.control.Alert;

public interface IViewController {
    void switchView(EPages page);
    void showAlert(Alert.AlertType type, String title, String header, String content);
}
