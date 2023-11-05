package State.Views;

import State.Controllers.ChatController;
import State.Exceptions.NoUserConnected;
import State.Models.Message;

import java.util.List;

public class ChatView extends AbstractView {
    private final ChatController controller;

    public ChatView() {
        super();
        this.controller = new ChatController(this);
    }

    @Override
    public void run() {}

    public void sendMessage(String content) throws NoUserConnected {
        // Get message from view
        controller.sendMessage(content);
    }

    public void displayNewMessage(Message message) {
        //Print new message info
    }

    public void displayAllMessages(List<Message> message) {

    }


}
