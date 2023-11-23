package Controllers;

import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.FeatureName;
import Models.Chat;
import Models.Message;
import Models.User;
import Services.ChatService;
import Services.FeatureService;
import Utils.AppState;
import Views.ChatView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatController extends AbstractController {

    private final ChatView view;
    private Chat chat;
    private ChatService chatService = ChatService.getInstance();
    private FeatureService featureService = FeatureService.getInstance();
    private AppState state = AppState.getInstance();

    public ChatController(ChatView view) {
        this.view = view;
        this.chat = chatService.getSelectedChat();

    }

    public void parseInput(int input) throws NoUserConnected {
        final int SEND = 1;
        final int SEE = 2;
        final int BACK = 3;
        final int RESEARCH = 4;
        final int EXIT = 0;

        switch (input) {
            case SEND -> view.sendMessage();
            case SEE -> view.displayAllMessages(chat.getMessages());
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

    public boolean hasLinkProtection() {
        return featureService.featureIsActivated(FeatureName.Link_Protection);
    }

    public List<Message> getNewListMessage() throws NoUserConnected, UserDoesntExist {
        return chatService.getListNewMessage(state.getConnectedUserName());
    }

    public void removeNewMessages() throws NoUserConnected {
        chat.removeNewMessages(state.getConnectedUser());
        //System.out.println(chat.getNewMessages());
    }


    public void removeAllNewMessages() throws NoUserConnected, UserDoesntExist {
        chatService.removeAllNewMessages();
    }
}
