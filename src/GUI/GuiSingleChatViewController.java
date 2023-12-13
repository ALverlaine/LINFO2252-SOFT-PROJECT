package GUI;

import Exceptions.NoUserConnected;
import GuiControllers.GuiChatController;
import GuiInterfaces.Views.IAppViewController;
import GuiWidget.GuiMessage;
import GuiWidget.ResearchDialog;
import Models.Message;
import ViewsAbstract.IChatView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class GuiSingleChatViewController extends AbstractViewController implements Initializable, IChatView {

    GuiChatController controller;
    @FXML
    VBox chatLog;
    @FXML
    TextArea messageBox;
    @FXML
    ScrollPane scrollPane;
    @FXML
    Button searchButton;
    public GuiSingleChatViewController(IAppViewController app) {
        super(app);
        this.app = app;
        this.controller = new GuiChatController(this);

    }

    @FXML
    public void onEnterSend() throws NoUserConnected{

    }

    @FXML
    public void setSendButtonAction() throws NoUserConnected {

        sendMessage();
    }

    public void sendMessage() throws NoUserConnected{

        String message = messageBox.getText();
        if (!message.isEmpty()) {
            controller.sendMessage(message);
        messageBox.clear();
        }
    }

    @Override
    public void displayNewMessageAfterSend(Message message) {
        displayMessage(message, true, false);

    }

    @Override
    public void displayMessages(List<Message> messages) {

    }

    @FXML
    public void goBack() {
        app.handleViewChange(EPages.CHAT_LIST);
    }

    public void displayMessages(List<Message> messages, String connectedUser, boolean hasLinkProtection) {
        for (Message message: messages) {
            String sender = message.getSender();
            if (Objects.equals(sender, connectedUser))
                displayMessage(message, true, false);
            else
                displayMessage(message, false, hasLinkProtection);
        }
    }

    public void displayMessage(Message message, boolean fromSender, boolean hasLinkProtection) {
        try {
            GuiMessage guiMessage = new GuiMessage(message, fromSender, hasLinkProtection);
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

    @FXML
    public void searchMessage() {
        ResearchDialog researchDialog = new ResearchDialog();
        Optional<String> toSearch = researchDialog.showAndWait();
    }

    public void removeSearchUI() {
        searchButton.setManaged(false);
    }
}
