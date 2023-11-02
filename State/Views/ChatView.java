package State.Views;

import State.Controllers.ChatController;
import State.Exceptions.NoUserConnected;
import State.Models.Chat;
import State.Models.Message;

import java.util.HashMap;

public class ChatView extends AbstractView {
    private final ChatController controller;

    public ChatView(HashMap<String, Object> params) {
        super(params);
        Chat chat = (Chat) params.get("chat");
        this.controller = new ChatController(this, chat);
    }

    @Override
    public void initialize() {}

    public void sendMessage(String content) throws NoUserConnected {
        // Get message from view
        controller.sendMessage(content);
    }

    public void displayNewMessage(Message message) {
        //Print new message info
    }


}
