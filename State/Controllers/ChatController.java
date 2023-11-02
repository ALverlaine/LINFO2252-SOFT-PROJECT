package State.Controllers;

import State.AppState;
import State.Models.Chat;
import State.Models.Message;
import State.Models.User;
import State.Views.ChatView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatController {

    AppState appstate;
    private final ChatView view;
    private Chat chat;

    public ChatController(ChatView view) {
        this.view = view;
    }

    public void addMessage(String content) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        List<User> users = chat.getUsers();
        //NEED TO GET SENDER FROM APPSTATE HERE BUT I HAVE TO GO
        //Message message = new Message(chat.getUsers());
        //chat.addMessage();
    }
}
