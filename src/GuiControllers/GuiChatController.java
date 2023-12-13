package GuiControllers;

import ControllersAbstract.AbstractChatController;
import Exceptions.NoUserConnected;
import Features.FeatureName;
import GUI.GuiSingleChatViewController;
import GuiInterfaces.Controllers.IChatGuiController;
import GuiInterfaces.Controllers.IController;
import Models.Message;

import java.util.List;

public class GuiChatController extends AbstractChatController implements IController {

    GuiSingleChatViewController view;
    public GuiChatController(GuiSingleChatViewController view) {
        super(view);
        this.view = view;
    }


    @Override
    public void initialize() {
        List<Message> messageList = chat.getMessages();
        boolean hasLinkProtection = featureService.featureIsActivated(FeatureName.Link_Protection);
        try {
            view.displayMessages(messageList, state.getConnectedUserName(), hasLinkProtection);
        }
        catch (NoUserConnected ignored) {}
        if (!featureService.featureIsActivated(FeatureName.Research)) view.removeSearchUI();
    }
}

