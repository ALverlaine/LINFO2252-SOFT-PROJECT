package Views;

import CliControllers.CliChatListController;
import Features.Theme;
import Models.Chat;
import Services.ChatService;
import ViewsAbstract.AbstractCLIView;
import ViewsAbstract.IChatListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ChatListCLIView extends AbstractCLIView implements IChatListView {

    private final CliChatListController controller;
    private ChatService chatService = ChatService.getInstance();
    public ChatListCLIView(AbstractCLIView previousView){
        super(previousView);
        this.controller = new CliChatListController(this);
    }

    @Override
    public AbstractCLIView run() {
        while (!exit) {
            int command;
            clearAll();
            printPrefix(controller.getChats());
            displayOptions();
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

    public void printPrefix(Map<String, Chat> chats) {
        StringBuilder chatsOutput = new StringBuilder();
        chatsOutput.append(Theme.BG + "Here are all your contacts ! \n");
        chatsOutput.append("-----Contacts----- \n");
        addAllChats(chats, chatsOutput);
        chatsOutput.append("------------------ \n");
        chatsOutput.append("What do you wish to do?" );
        System.out.println(chatsOutput);
    }

    public void goToChat() {
        nextView = new ChatCLIView(this);
        exit = true;
    }

    public String selectChat() {
        System.out.print("Enter the name of the user you want:\n");
        return scanner.nextLine();
    }

    @Override
    public void goBack() {
        nextView = new MenuCLIView(null);
        exit = true;
    }

    @Override
    protected List<String> getMenuOptions() {
        return new ArrayList<>(List.of(
                "Open a chat",
                "Add a new chat",
                "Delete a chat",
                "Go back"
        ));
    }

    public void addAllChats(Map<String, Chat> chats, StringBuilder chatsOutput) {
        int i = 1;
        for (String user : chats.keySet()) {
            String userString = user;
            //System.out.println(controller.statusActivated());
            if (controller.statusActivated())
                userString = user + " - Status: " + controller.getStatus(user);
            chatsOutput.append(i).append(". ").append(userString).append("\n");
            i++;
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

    public void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }



}