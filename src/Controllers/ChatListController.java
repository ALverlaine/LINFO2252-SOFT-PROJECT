package Controllers;

import Exceptions.AlreadyHasChat;
import Exceptions.HasNoChat;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.FeatureName;
import Services.FeatureService;
import Models.Chat;
import Services.ChatService;
import Utils.AppState;
import Views.ChatListView;

import java.util.Map;

public class ChatListController extends AbstractController {
    private Map<String, Chat> chats;
    private ChatListView view;
    private ChatService chatService = ChatService.getInstance();
    private FeatureService featureService = FeatureService.getInstance();
    private AppState state = AppState.getInstance();

    public ChatListController(ChatListView view){
        this.view = view;
        try {
            this.chats = chatService.getUserChats(state.getConnectedUserName());
        }
        catch (NoUserConnected | UserDoesntExist ignored) {}
    }


    public void parseInput(int input) {
        final int OPEN = 1;
        final int ADD = 2;
        final int DELETE = 3;
        final int BACK = 4;
        final int EXIT = 5;
        String receiver = "";
        if (input <= DELETE && input >= OPEN) {
            receiver = view.selectChat();
        }

        switch(input) {
            case OPEN -> goToChat(receiver);
            case ADD -> modifyChat(receiver, true);
            case DELETE -> modifyChat(receiver, false);
            case BACK -> view.goBack();
            case EXIT -> view.exit();
            default -> view.inputNotRecognized();
        }
    }

    public void goToChat(String receiver) {
       try {
           chatService.setSelectedChat(receiver);
           view.goToChat();
           //Put chat screen
       }
       catch (UserDoesntExist e1) {view.userDoesntExist(receiver);}
       catch (HasNoChat e2) {view.hasNoChat(receiver);}
       catch (NoUserConnected ignored) {}
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

    public boolean statusActivated() {
        return featureService.featureIsActivated(FeatureName.Status);
    }

    public String getStatus(String username) {
        return featureService.getUserStatus(username);
    }

    public Map<String, Chat> getChats() {
        return chats;
    }

}