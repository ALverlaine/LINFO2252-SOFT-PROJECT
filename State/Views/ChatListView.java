package State.Views;

import State.Controllers.ChatListController;
import State.Models.Chat;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ChatListView extends AbstractView {
    private final ChatListController controller;
    List<Chat> chats;

    public ChatListView(HashMap<String, Object> params) {
        super(params);
        List<Chat> chats = (List<Chat>) params.get("chats");
        this.controller = new ChatListController(this);
    }

    @Override
    public void initialize() {}
}
