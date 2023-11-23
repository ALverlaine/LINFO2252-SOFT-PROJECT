package Services;

import Exceptions.AlreadyHasChat;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;

import Models.Chat;
import Models.Message;
import Models.User;
import Utils.AppState;
public class Database {
    public static void populateUsersDB(AppState appState) throws AlreadyHasChat {

        ChatService chatService = ChatService.getInstance();
        User kim = new User("kim", "123");
        User fra = new User("fra", "456");
        User alex = new User("alex", "789");
        User sox = new User("sox", "sox");
        appState.addUser(kim);
        appState.addUser(fra);
        appState.addUser(alex);
        appState.addUser(sox);
    }

    public static void populateChatsDB(AppState appState) throws UserDoesntExist, NoUserConnected, AlreadyHasChat {
        appState.setConnectedUser(appState.getUser("kim"));
        User fra = appState.getUser("fra");
        User alex = appState.getUser("alex");

        //User d = state.getUser("d");
        Chat chat1 = new Chat(appState.getConnectedUser(), fra);
        Chat chat2 = new Chat(appState.getConnectedUser(), alex);

        //Chat chat3 = new Chat(state.getConnectedUser(), d);

        ChatService chatService = ChatService.getInstance();
        chatService.addChat("fra", chat1);
        chatService.addChat("alex", chat2);

        Message message1 = new Message(appState.getConnectedUser(), fra, "Hello", "2021-05-05 12:00:00");
        Message message2 = new Message(appState.getConnectedUser(), fra, "How are you?", "2021-05-05 12:00:30");

        chat1.addMessage(message1);
        chat1.addMessage(message2);

        appState.setConnectedUser(appState.getUser("fra"));

        Message message3 = new Message(appState.getConnectedUser(), alex, "Hello, fine and you ?", "2021-05-05 12:20:00");
        chat1.addMessage(message3);

    }

}
