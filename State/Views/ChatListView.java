package State.Views;

import State.Controllers.ChatListController;
import State.Models.Chat;

import java.util.Map;
import java.util.HashMap;

public class ChatListView extends AbstractView {

    private final ChatListController controller;
    public ChatListView(HashMap<String, Object> params) {
        super(params);
        Map<String, Chat> chats = (Map<String, Chat>) params.get("chats");
        this.controller = new ChatListController(this, chats);
    }

    @Override
    public void initialize() {}

    public void selectChat() {
        //Let user choose from input
    }

    public void displayAllChats(Map<String, Chat> chats) {
    }
}