package State.Controllers;

import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;
import State.Features.FeatureName;
import State.Features.Status;
import State.Models.Chat;
import State.Models.Message;
import State.Models.User;
import State.Services.ChatService;
import State.Services.FeatureService;
import State.Views.ChatView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import State.State;

public class ChatController extends AbstractController {

    private final ChatView view;
    private Chat chat;
    private ChatService chatService = ChatService.getInstance();
    private FeatureService featureService = FeatureService.getInstance();

    public ChatController(ChatView view) {
        this.view = view;
        this.chat = chatService.getSelectedChat();
        if(chat != null) view.displayAllMessages(chat.getMessages());
        else System.out.println("There is no chat");
    }

    public void parseInput(int input) throws NoUserConnected {
        final int SEND = 1;
        final int SEE = 2;
        final int SEE_NEW = 3;
        final int BACK = 4;
        final int EXIT = 5;
        final int RESEARCH = 6;

        switch (input) {
            case SEND -> view.sendMessage();
            case SEE -> view.displayAllMessages(chat.getMessages());
            case SEE_NEW -> view.displayNewMessage(chat.getNewMessages(state.getConnectedUser()));
            case BACK -> view.goBack();
            case EXIT -> view.exit();
            case RESEARCH -> researchMessages();
            default -> view.inputNotRecognized();
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
        chat.addNewMessage(message, sender);
        try{
            view.displayNewMessageAfterSend(message);
        }catch (Exception ignored){}

    }

    public void researchMessages() {
        if (canResearch()) {
            view.displayMessages(
                    chat.findMessage(
                            view.enterMessageToSearch()));
        }
    }

    public boolean canResearch() {
        return featureService.researchActivated();
    }

    public void removeNewMessages() throws NoUserConnected {
        chat.removeNewMessages(state.getConnectedUser());
        //System.out.println(chat.getNewMessages());
    }



}
