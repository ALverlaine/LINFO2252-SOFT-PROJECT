package CliControllers;

import Controllers.ChatListController;
import ControllersAbstract.AbstractChatListController;
import Views.ChatListCLIView;

public class CliChatListController extends AbstractChatListController {

    private ChatListCLIView view;

    public CliChatListController(ChatListCLIView view) {
        super(view);
        this.view = view;
    }

    public void parseInput(int input) {
        final int OPEN = 1;
        final int ADD = 2;
        final int DELETE = 3;
        final int BACK = 4;
        final int EXIT = 5;
        String receiver = "";
        if (input <= DELETE && input >= OPEN) {
            receiver = view.selectChat();
        }

        switch(input) {
            case OPEN -> goToChat(receiver);
            case ADD -> modifyChat(receiver, true);
            case DELETE -> modifyChat(receiver, false);
            case BACK -> view.goBack();
            case EXIT -> view.exit();
            default -> view.inputNotRecognized();
        }
    }

}
