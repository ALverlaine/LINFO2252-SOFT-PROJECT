package GUI;

import Controllers.ChatListController;
import ControllersAbstract.AbstractChatListController;
import GuiControllers.GuiChatListController;
import GuiInterfaces.Controllers.IChatListGuiController;
import GuiInterfaces.Views.IAppViewController;
import GuiInterfaces.Views.IChatListViewController;
import GuiWidget.ChatBoxComponent;
import GuiWidget.ChatCreationDialog;
import Models.Chat;
import ViewsAbstract.IChatListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChatListViewController extends AbstractViewController implements Initializable, IChatListView {

    private final GuiChatListController controller;
    public HBox optionsBox;
    IAppViewController app;
    @FXML
    VBox chatList;

    public ChatListViewController(IAppViewController app) {
        super(app);
        this.app = app;
        controller = new GuiChatListController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller.initialize();
    }


    public void addChat(Chat chat, String receiver) {
        ChatBoxComponent chatBox;
        System.out.println(receiver);
        try {
            chatBox = new ChatBoxComponent(chat, receiver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addChatHandlers(chatBox);
        chatList.getChildren().add(chatBox);
    }

    public void addChats(Map<String, Chat> chats) {
        chatList.getChildren().clear();
        for (Map.Entry<String, Chat> chat: chats.entrySet()) {
            String username = chat.getKey();
            Chat userChat = chat.getValue();
            addChat(userChat, username);
        }
    }

    private void addChatHandlers(ChatBoxComponent chatBoxComponent) {
        chatBoxComponent.setOpenChatHandler(this::handleOpenChat);
        chatBoxComponent.setDeleteChatHandler(this::handleDeleteChat);
    }

    @FXML
    public void handleAddChat() {
        ChatCreationDialog chatCreationDialog = new ChatCreationDialog();
        Optional<String> username = chatCreationDialog.showAndWait();
        username.ifPresent(list -> controller.modifyChat(String.valueOf(username.get()), true));
    }

    public void handleOpenChat(String receiver) {
        controller.goToChat(receiver);
    }

    public void handleDeleteChat(String receiver) {
        controller.modifyChat(receiver, false);
    }

    @Override
    public void goToChat() {
        app.handleViewChange(EPages.CHAT);
    }

    @Override
    public void userDoesntExist(String receiver) {
        showAlert(Alert.AlertType.ERROR, "The user you selected doesn't exist", "", "");
    }

    @Override
    public void hasNoChat(String receiver) {
        showAlert(Alert.AlertType.ERROR, "You have no chats selected with this user", "", "");
    }

    @Override
    public void alreadyHasChat(String receiver) {
        showAlert(Alert.AlertType.ERROR, "You already have a chat with the user", "", "");
    }
}
