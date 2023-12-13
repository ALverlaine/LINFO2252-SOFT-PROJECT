package GuiWidget;

import Models.Chat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class ChatBoxComponent extends HBox {
    @FXML
    Text chatReceiver;

    private Chat chat;
    String receiver;
    private ChatBoxComponentHandler<String> openChatHandler;
    private ChatBoxComponentHandler<String> deleteChatHandler;

    public interface ChatBoxComponentHandler<T> {
        void handle(T event);
    }

    public ChatBoxComponent(Chat chat, String receiver) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Components/ChatBox/layoutLightMode.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(ChatBoxComponent.this);
        this.setSpacing(50);
        this.getStyleClass().add("section-line");
        this.setAlignment(javafx.geometry.Pos.CENTER);
        fxmlLoader.load();
        this.chat = chat;
        this.receiver = receiver;
        this.chatReceiver.setText(receiver);
        this.refresh();
    }

    private void refresh() {
        if (chat != null) {
            chatReceiver.setText(receiver);
        }
    }

    public void setOpenChatHandler(ChatBoxComponentHandler<String> handler) {
        this.openChatHandler = handler;
    }

    public void setDeleteChatHandler(ChatBoxComponentHandler<String> handler) {
        this.deleteChatHandler = handler;
    }

    @FXML
    public void openChat() {
        if (openChatHandler != null) {
            openChatHandler.handle(receiver);
        }
    }

    public void deleteChat() {
        if (deleteChatHandler != null) {
            deleteChatHandler.handle(receiver);
        }
    }

}
