package State.Controllers;

import State.Exceptions.NoUserConnected;
import State.Models.Chat;
import State.Models.Message;
import State.Models.User;
import State.Services.ChatService;
import State.Views.ChatView;
import java.util.Date;
import java.util.List;
import State.State;

public class ChatController extends AbstractController {

    private final ChatView view;
    private Chat chat;
    private ChatService chatService = ChatService.getInstance();

    public ChatController(ChatView view) {
        this.view = view;
        this.chat = chatService.getSelectedChat();
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
