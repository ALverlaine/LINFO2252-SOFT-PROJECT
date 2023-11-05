package State.Views;

import State.Controllers.ChatListController;
import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;
import State.Models.Chat;

import java.util.Map;
import java.util.HashMap;

public class ChatListView extends AbstractView {

    private final ChatListController controller;
    public ChatListView() throws NoUserConnected, UserDoesntExist {
        super();
        this.controller = new ChatListController(this);
    }

    @Override
    public void run() {}

    public void selectChat() {
        //Let user choose from input
    }

    public void displayAllChats(Map<String, Chat> chats) {
    }

    public void userDoesntExist() {

    }

    public void alreadyHasChat() {
        //SHow that he already has as contact
    }

    public void hasNoChat() {
        //Show that has no chat with user
    }
}