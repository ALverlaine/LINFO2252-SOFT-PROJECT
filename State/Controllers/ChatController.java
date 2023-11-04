package State.Controllers;

import State.Exceptions.NoUserConnected;
import State.Models.Chat;
import State.Models.Message;
import State.Models.User;
import State.Views.ChatView;
import java.util.Date;
import java.util.List;
import State.State;

public class ChatController extends AbstractController {

    State state = State.getInstance();
    private final ChatView view;
    private Chat chat;

    public ChatController(ChatView view, Chat chat) {
        this.view = view;
        this.chat = chat;
        view.displayAllMessages(chat.getMessages());
    }

    public void sendMessage(String content) throws NoUserConnected {
        Date date = new Date();
        List<User> users = chat.getChatUsers();
        User sender = state.getConnectedUser();

        User receiver = users.get(0).equals(sender) ? users.get(1) : users.get(0);
        Message message = new Message(sender, receiver, content, date);

        chat.addMessage(message);

        view.displayNewMessage(message);
    }
}
