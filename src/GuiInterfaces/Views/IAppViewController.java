package GuiInterfaces.Views;
import GUI.EPages;
import javafx.stage.Window;

public interface IAppViewController {
    Window getWindow();
    void handleViewChange(EPages page);
    void setTitle(String title);
}