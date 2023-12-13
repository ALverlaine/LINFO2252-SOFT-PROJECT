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
import Views.ChatListCLIView;

import java.util.Map;

public class ChatListController {
    private Map<String, Chat> chats;
    private ChatListCLIView view;
    private ChatService chatService = ChatService.getInstance();
    private FeatureService featureService = FeatureService.getInstance();
    private AppState state = AppState.getInstance();

    public ChatListController(ChatListCLIView view){
        this.view = view;
        try {
            this.chats = chatService.getUserChats(state.getConnectedUserName());
        }
        catch (NoUserConnected | UserDoesntExist ignored) {}
    }


}
