package State.Views;

import State.Controllers.ChatController;

public class ChatView {
    private final ChatController controller;

    public ChatView() {
        this.controller = new ChatController(this);
    }

    public void sendMessage(String content) {
        // Get message from view
        controller.addMessage(content);
    }
}
