package State.Services;

import State.Exceptions.AlreadyHasChat;
import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;

import State.Models.Chat;
import State.Models.User;
import State.State;
public class Database {
    public static void populateUsersDB(State state) throws AlreadyHasChat {

        ChatService chatService = ChatService.getInstance();
        User a = new User("a", "a");
        User b = new User("b", "b");
        User c = new User("c", "c");
        User d = new User("d", "d");
        state.addUser(a);
        state.addUser(b);
        state.addUser(c);
        state.addUser(d);
    }

    public static void populateChatsDB(State state) throws UserDoesntExist, NoUserConnected, AlreadyHasChat {
        state.setConnectedUser(state.getUser("a"));
        User b = state.getUser("b");
        User c = state.getUser("c");
        //User d = state.getUser("d");
        Chat chat1 = new Chat(state.getConnectedUser(), b);
        Chat chat2 = new Chat(state.getConnectedUser(), c);
        //Chat chat3 = new Chat(state.getConnectedUser(), d);
        ChatService chatService = ChatService.getInstance();

        chatService.addChat("b", chat1);
        chatService.addChat("c", chat2);

    }

}
