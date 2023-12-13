package GuiControllers;

import GuiInterfaces.Controllers.IController;
import GuiInterfaces.Views.IViewController;
import javafx.scene.control.Alert;

public abstract class AbstractGuiController implements IController {
    protected IViewController view;


    /**
     * Constructor of the AbstractController class.
     *
     * @param view the view controller that the controller interacts with.
     */
    AbstractGuiController(IViewController view) {
        this.view = view;
    }

    /**
     * Initializes the controller. This is used as an alternative way to make
     * initialization procedures to the constructor, to ensure the FXML has
     * been already properly loaded.
     */
    @Override
    public void initialize() {
    }


    /**
     * Method to show an error alert for the given exception with the specified title.
     *
     * @param e     the exception to display in the alert.
     * @param title the title of the alert.
     */
    protected void showAlertForException(Exception e, String title) {
        showAlertForException(title, e.getMessage(), "");
    }

    /**
     * Method to show an error alert with the specified title, header and content.
     *
     * @param title   the title of the alert.
     * @param header  the header of the alert.
     * @param content the content of the alert.
     */
    protected void showAlertForException(String title, String header, String content) {
        view.showAlert(Alert.AlertType.ERROR, title, header, content);
    }
}

