package State.Controllers;

import State.Models.Chat;
import State.Views.ChatListView;

import java.util.List;

public class ChatListController {
    private List<Chat> chats;
    private ChatListView view;

    public ChatListController(ChatListView view) {
        this.view = view;
    }
}
