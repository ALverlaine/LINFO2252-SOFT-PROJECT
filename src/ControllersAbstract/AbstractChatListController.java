package ControllersAbstract;

import Exceptions.AlreadyHasChat;
import Exceptions.HasNoChat;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.FeatureName;
import Models.Chat;
import Services.ChatService;
import Services.FeatureService;
import Utils.AppState;
import Views.ChatListCLIView;
import ViewsAbstract.IChatListView;

import java.util.Map;

public class AbstractChatListController{
    IChatListView view;
    protected Map<String, Chat> chats;
    protected ChatService chatService = ChatService.getInstance();
    protected FeatureService featureService = FeatureService.getInstance();
    protected AppState state = AppState.getInstance();

    public AbstractChatListController(IChatListView view) {
        this.view = view;
        try {
            this.chats = chatService.getUserChats(state.getConnectedUserName());
        }
        catch (NoUserConnected | UserDoesntExist ignored) {}
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
