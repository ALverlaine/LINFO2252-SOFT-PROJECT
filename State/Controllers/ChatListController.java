package State.Controllers;

import State.Exceptions.AlreadyHasChat;
import State.Exceptions.HasNoChat;
import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;
import State.Models.Chat;
import State.Services.ChatService;
import State.Views.ChatListView;
import State.State;
import java.util.Map;

public class ChatListController extends AbstractController {
    private Map<String, Chat> chats;
    private ChatListView view;
    private ChatService chatService = ChatService.getInstance();

    public ChatListController(ChatListView view) throws NoUserConnected, UserDoesntExist {
        this.view = view;
        this.chats = chatService.getUserChats(state.getConnectedUserName());
    }


    public void parseInput(int input) {
        final int OPEN = 1;
        final int ADD = 2;
        final int DELETE = 3;
        final int BACK = 4;
        String receiver = "";
        if (input <= DELETE && input >= OPEN) {
            receiver = view.selectChat();
        }

        switch(input) {
            case OPEN -> goToChat(receiver);
            case ADD -> modifyChat(receiver, true);
            case DELETE -> modifyChat(receiver, false);
            case BACK -> view.exitView();
            default -> view.inputNotRecognized();
        }
    }

    public void goToChat(String receiver) {
       try {
           chatService.setSelectedChat(receiver);
           view.exitView();
           //Put chat screen
       }
       catch (UserDoesntExist e1) {view.userDoesntExist(receiver);}
       catch (HasNoChat e2) {view.hasNoChat(receiver);}
       catch (NoUserConnected e) {
       }
    }

    public void modifyChat(String receiver, boolean isAdding) {
        boolean userExists = state.userExists(receiver);
        if (!userExists) {
            view.userDoesntExist(receiver);
            return;
        }

        if (isAdding) {
            try {
                Chat chat = new Chat(state.getConnectedUser(), state.getUser(receiver));
                chatService.addChat(receiver, chat);
            }
            catch (NoUserConnected | UserDoesntExist ignored) {} //Can never happen
            catch (AlreadyHasChat e) {view.alreadyHasChat(receiver);}
        }

        else {
            try { chatService.removeChat(receiver); }
            catch (HasNoChat e) {view.hasNoChat(receiver);}
        }
    }

    public Map<String, Chat> getChats() {
        return chats;
    }
}
