package GuiControllers;

import ControllersAbstract.AbstractChatController;
import Exceptions.NoUserConnected;
import GUI.GuiSingleChatViewController;
import GuiInterfaces.Controllers.IChatGuiController;
import Models.Message;
import Models.User;

import java.io.IOException;
import java.util.List;

public class GuiChatController extends AbstractChatController implements IChatGuiController {

    GuiSingleChatViewController view;
    public GuiChatController(GuiSingleChatViewController view) {
        super(view);
        this.view = view;
    }


    @Override
    public void initialize() {
        List<Message> messageList = chat.getMessages();
        try {
            view.displayMessages(messageList, state.getConnectedUserName());
        }
        catch (NoUserConnected ignored) {}
    }
}

