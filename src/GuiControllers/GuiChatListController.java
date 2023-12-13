package GuiControllers;

import ControllersAbstract.AbstractChatListController;
import GUI.ChatListViewController;
import GuiInterfaces.Controllers.IChatListGuiController;
import GuiInterfaces.Views.IChatListViewController;
import Models.Chat;
import ViewsAbstract.IChatListView;

import java.util.Map;

public class GuiChatListController extends AbstractChatListController implements IChatListGuiController {
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
    }
    @Override
    public void modifyChat(String receiver, boolean isAdding) {
        super.modifyChat(receiver, isAdding);
        view.addChats(chats);
    }


}
