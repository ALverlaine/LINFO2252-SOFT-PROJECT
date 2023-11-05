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
        view.displayAllChats(chats);
    }

    public void goToChat(String receiver) {
       try {
           chatService.setSelectedChat(receiver);
           //Also switch view
       }
       catch (UserDoesntExist e1) {view.userDoesntExist();}
       catch (HasNoChat e2) {view.hasNoChat();}
    }

    public void modifyChat(String receiver, boolean isAdding) {
        boolean userExists = state.userExists(receiver);
        if (!userExists) {
            view.userDoesntExist();
            return;
        }

        if (isAdding) {
            try {
                Chat chat = new Chat(state.getConnectedUser(), state.getUser(receiver));
                chatService.addChat(receiver, chat);
            }
            catch (NoUserConnected | UserDoesntExist ignored) {} //Can never happen
            catch (AlreadyHasChat e) {view.alreadyHasChat();}
        }

        else {
            try { chatService.removeChat(receiver); }
            catch (HasNoChat e) {view.hasNoChat();}
        }
    }


}
