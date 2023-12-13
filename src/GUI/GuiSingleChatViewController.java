package GUI;

import Exceptions.NoUserConnected;
import GuiControllers.GuiChatController;
import GuiInterfaces.Views.IAppViewController;
import GuiWidget.GuiMessage;
import Models.Message;
import ViewsAbstract.IChatView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class GuiSingleChatViewController extends AbstractViewController implements Initializable, IChatView {

    GuiChatController controller;
    @FXML
    VBox chatLog;
    @FXML
    TextArea messageBox;
    public GuiSingleChatViewController(IAppViewController app) {
        super(app);
        this.app = app;
        this.controller = new GuiChatController(this);
    }

    @FXML
    public void onEnterSend() {

    }

    @FXML
    public void setSendButtonAction() throws NoUserConnected {
        String message = messageBox.getText();
        controller.sendMessage(message);
    }

    @Override
    public void displayNewMessageAfterSend(Message message) {
        displayMessage(message, true);
    }

    @Override
    public void displayMessages(List<Message> messages) {

    }

    public void displayMessages(List<Message> messages, String connectedUser) {
        for (Message message: messages) {
            String sender = message.getSender();
            if (Objects.equals(sender, connectedUser))
                displayMessage(message, true);
            else
                displayMessage(message, false);
        }
    }

    public void displayMessage(Message message, boolean fromSender) {
        try {
            GuiMessage guiMessage = new GuiMessage(message, fromSender);
            chatLog.getChildren().add(guiMessage);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String enterMessageToSearch() {
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller.initialize();
    }
}
