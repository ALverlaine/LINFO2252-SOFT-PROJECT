package Services;

import Exceptions.AlreadyHasChat;
import Exceptions.HasNoChat;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Models.Chat;
import Models.Message;
import Utils.AppState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatService {
    private Map<String, Map<String, Chat>> chats = new HashMap<>();
    private AppState appState = AppState.getInstance();
    private static ChatService instance = null;
    private Chat selectedChat;

    private ChatService() {

    }

    public static ChatService getInstance() {
        if (instance == null) instance = new ChatService();
        return instance;
    }

    public Map<String, Chat> getUserChats(String username) throws UserDoesntExist {
        boolean userExists = appState.userExists(username);

        if (!userExists) throw new UserDoesntExist();

        if (!chats.containsKey(username)) chats.put(username, new HashMap<>());
        return chats.get(username);
    }

    public Chat getChatWith(String receiver) throws HasNoChat, UserDoesntExist, NoUserConnected {
        Map<String,Chat> userChats = getUserChats(appState.getConnectedUserName());
        Chat chat = userChats.get(receiver);
        if (chat == null) throw new HasNoChat();
        return chat;
    }

    public boolean hasChatWithUser(String receiver) {
        try {
            Map<String, Chat> userChats = getUserChats(appState.getConnectedUserName());
            return userChats.get(receiver) != null;
        }
        catch (NoUserConnected | UserDoesntExist ignored) {}
        return false;
    }

    public void addChat(String receiver, Chat newChat) throws AlreadyHasChat {
        try {
            if (hasChatWithUser(receiver)) throw new AlreadyHasChat();

            Map<String, Chat> userChats = getUserChats(appState.getConnectedUserName());
            Map<String, Chat> receiverChats = getUserChats(receiver);

            userChats.put(receiver, newChat);
            receiverChats.put(appState.getConnectedUserName(), newChat);
        }
        catch (NoUserConnected | UserDoesntExist ignored) {}
    }

    public void removeChat(String receiver) throws HasNoChat {
        try {
            if (!hasChatWithUser(receiver)) throw new HasNoChat();

            Map<String, Chat> userChats = getUserChats(appState.getConnectedUserName());
            Map<String, Chat> receiverChats = getUserChats(receiver);

            userChats.remove(receiver);
            receiverChats.remove(receiver);

        } catch (NoUserConnected | UserDoesntExist ignored) {}
    }

    public void setSelectedChat(String receiver) throws HasNoChat, UserDoesntExist, NoUserConnected {
        selectedChat = getChatWith(receiver);
    }

    public Chat getSelectedChat() {
        return selectedChat;
    }
    public List<Message> getListNewMessage(String username) throws NoUserConnected, UserDoesntExist {
        List<Message> messages = new ArrayList<>();
        Map<String, Chat> userChats = getUserChats(appState.getConnectedUserName());
        for (String user : userChats.keySet()) {
            messages.addAll(userChats.get(user).getNewMessages(appState.getConnectedUser()));
        }
        return messages;
    }

    public void removeAllNewMessages() throws NoUserConnected, UserDoesntExist {
        Map<String, Chat> userChats = getUserChats(appState.getConnectedUserName());
        for (String user : userChats.keySet()) {
            userChats.get(user).removeAllNewMessages(appState.getConnectedUser());
        }
    }
}
