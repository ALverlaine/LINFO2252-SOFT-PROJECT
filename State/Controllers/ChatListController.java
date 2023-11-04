package State.Controllers;

import State.Models.Chat;
import State.Views.ChatListView;

import java.util.Map;

public class ChatListController {
    private Map<String, Chat> chats;
    private ChatListView view;

    public ChatListController(ChatListView view, Map<String, Chat> chats) {
        this.view = view;
        this.chats = chats;
        view.displayAllChats(chats);
    }

    public void goToChat(String username) {

    }
}
