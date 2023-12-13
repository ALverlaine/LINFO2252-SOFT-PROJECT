package CliControllers;

import Controllers.ChatController;
import Exceptions.NoUserConnected;
import Views.ChatCLIView;
import ViewsAbstract.IChatView;

public class CliChatController extends ChatController {
    private final ChatCLIView view;

    public CliChatController(ChatCLIView view) {
        super(view);
        this.view = view;
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


}
