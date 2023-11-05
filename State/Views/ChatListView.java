package State.Views;

import State.Controllers.ChatListController;
import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;
import State.Models.Chat;
import State.Services.ChatService;

import java.util.Map;
import java.util.Scanner;

public class ChatListView extends AbstractView {

    private final ChatListController controller;
    private ChatService chatService = ChatService.getInstance();
    public ChatListView(AbstractView previousView) throws NoUserConnected, UserDoesntExist {
        super(previousView);
        this.controller = new ChatListController(this);
    }

    @Override
    public AbstractView run() {
        while (!exit) {
            int command;
            clearAll();

            createOutput(controller.getChats());
            try {
                command = Integer.parseInt(scanner.nextLine());
                controller.parseInput(command);
            }
            //Reset scanner to avoid faulty input being input twice
            catch (NumberFormatException e) {scanner = new Scanner(System.in);
            }
        }
        exit = false;
        return nextView;
    }

    public void createOutput(Map<String, Chat> chats) {
        StringBuilder chatsOutput = new StringBuilder();
        chatsOutput.append("Here are all your available chats \n");
        chatsOutput.append("-----Chats----- \n");
        addAllChats(chats, chatsOutput);
        chatsOutput.append("-----End of chats----- \n");

        chatsOutput.append("""
                What do you wish to do?\s
                (1) Open a chat\s
                (2) Add a new chat\s
                (3) Delete a chat\s
                (4) Go back\s
                Enter your choice: """);
        System.out.println(chatsOutput);
    }

    public void goToChat() {
        nextView = new ChatView(this);
        exit = true;
    }

    public String selectChat() {
        System.out.print("Select the user you want you want to apply the operation to");
        return scanner.nextLine();

    }

    public void addAllChats(Map<String, Chat> chats, StringBuilder chatsOutput) {
        for (String user : chats.keySet()) {
            chatsOutput.append(user).append("\n");
        }
    }

    public void userDoesntExist(String receiver) {
        System.out.println("User " + receiver + " doesn't exist");
    }

    public void alreadyHasChat(String receiver) {
        System.out.println("You already have a chat with user " + receiver);
    }

    public void hasNoChat(String receiver) {
        System.out.println("You have no chats with user " + receiver);
    }



}