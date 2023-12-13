package ControllersAbstract;

import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.FeatureName;
import Models.Chat;
import Models.Message;
import Models.User;
import Services.ChatService;
import Services.FeatureService;
import Utils.AppState;
import Views.ChatCLIView;
import ViewsAbstract.IChatView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AbstractChatController {

    private final IChatView view;
    protected Chat chat;
    protected ChatService chatService = ChatService.getInstance();
    protected FeatureService featureService = FeatureService.getInstance();
    protected AppState state = AppState.getInstance();

    public AbstractChatController(IChatView view) {
        this.view = view;
        this.chat = chatService.getSelectedChat();
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
