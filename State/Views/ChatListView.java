package State.Views;

import State.Controllers.ChatListController;

public class ChatListView {
    private final ChatListController controller;

    public ChatListView() {
        this.controller = new ChatListController(this);
    }
}
