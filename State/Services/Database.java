package State.Services;

import State.Controllers.ChatController;
import State.Exceptions.AlreadyHasChat;
import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;

import State.Models.Chat;
import State.Models.Message;
import State.Models.User;
import State.State;
public class Database {
    public static void populateUsersDB(State state) throws AlreadyHasChat {

        ChatService chatService = ChatService.getInstance();
        User kim = new User("kim", "123");
        User fra = new User("fra", "456");
        User alex = new User("alex", "789");
        User sox = new User("sox", "sox");
        state.addUser(kim);
        state.addUser(fra);
        state.addUser(alex);
        state.addUser(sox);
    }

    public static void populateChatsDB(State state) throws UserDoesntExist, NoUserConnected, AlreadyHasChat {
        state.setConnectedUser(state.getUser("kim"));
        User fra = state.getUser("fra");
        User alex = state.getUser("alex");

        //User d = state.getUser("d");
        Chat chat1 = new Chat(state.getConnectedUser(), fra);
        Chat chat2 = new Chat(state.getConnectedUser(), alex);

        //Chat chat3 = new Chat(state.getConnectedUser(), d);

        ChatService chatService = ChatService.getInstance();
        chatService.addChat("fra", chat1);
        chatService.addChat("alex", chat2);

        Message message1 = new Message(state.getConnectedUser(), fra, "Hello", "2021-05-05 12:00:00");
        Message message2 = new Message(state.getConnectedUser(), fra, "How are you?", "2021-05-05 12:00:30");

        chat1.addMessage(message1);
        chat1.addMessage(message2);

        state.setConnectedUser(state.getUser("fra"));

        Message message3 = new Message(state.getConnectedUser(), alex, "Hello, fine and you ?", "2021-05-05 12:20:00");
        chat1.addMessage(message3);

    }

}
