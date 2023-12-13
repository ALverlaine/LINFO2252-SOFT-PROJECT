package GuiControllers;

import ControllersAbstract.AbstractChatListController;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.FeatureName;
import GUI.ChatListViewController;
import GuiInterfaces.Controllers.IChatListGuiController;
import GuiInterfaces.Controllers.IController;
import GuiInterfaces.Views.IChatListViewController;
import Models.Chat;
import Models.Message;
import ViewsAbstract.IChatListView;

import java.util.List;
import java.util.Map;

public class GuiChatListController extends AbstractChatListController implements IController {
    private ChatListViewController view;

    public GuiChatListController(ChatListViewController view) {
        super(view);
        this.view = view;
    }

    @Override
    public void initialize() {
        for (Map.Entry<String, Chat> chat: chats.entrySet()) {
            String receiver = chat.getKey();
            Chat userChat = chat.getValue();
            view.addChat(userChat, receiver);
        }
        showNotifications();
        if (featureService.featureIsActivated(FeatureName.Driving)) view.removeDrivingUI();
        if (featureService.featureIsActivated(FeatureName.DND)) view.removeDndUI();
    }
    @Override
    public void modifyChat(String receiver, boolean isAdding) {
        super.modifyChat(receiver, isAdding);
        view.addChats(chats);
    }

    public void showNotifications() {
        try {
            List<Message> messages = super.getNewListMessage();
            view.showNotifications(messages);
        }
        catch (NoUserConnected | UserDoesntExist e) {

        }
    }

}
