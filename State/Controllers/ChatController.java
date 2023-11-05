package State.Controllers;

import State.Exceptions.NoUserConnected;
import State.Models.Chat;
import State.Models.Message;
import State.Models.User;
import State.Services.ChatService;
import State.Views.ChatView;

import java.text.SimpleDateFormat;
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
        if(chat != null) view.displayAllMessages(chat.getMessages());
        else System.out.println("There is no chat");
    }

    public void parseInput(int input) {
        final int SEND = 1;
        final int SEE = 2;
        final int BACK = 4;

        switch (input) {
            case SEND -> view.sendMessage();
            case SEE -> view.displayAllMessages(chat.getMessages());
            case BACK -> view.goBack();
        }
    }

    public void sendMessage(String content) throws NoUserConnected {
        Date date = new Date();
        List<User> users = chat.getChatUsers();
        User sender = state.getConnectedUser();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss "); // Customize the format as needed
        String formattedDate = dateFormat.format(date);

        User receiver = users.get(0).equals(sender) ? users.get(1) : users.get(0);
        Message message = new Message(sender, receiver, content, formattedDate);

        chat.addMessage(message);
        try{
            view.displayNewMessage(message);
        }catch (Exception ignored){}

    }

    public Chat getChat(User user) {
        try {
            return chatService.getChatWith(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public List<User> getChatUsers() {
        return chat.getChatUsers();
    }

}
