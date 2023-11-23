package Views;

import Controllers.ChatController;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Models.Message;
import Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatView extends AbstractView {
    private final ChatController controller;
    Scanner scanner = new Scanner(System.in);

    public ChatView(AbstractView previousView) {
        super(previousView);
        this.controller = new ChatController(this);
    }

    @Override
    public AbstractView run() {
        while (!exit) {
            displayOptions();
            try {
                int command = Integer.parseInt(scanner.nextLine());
                controller.parseInput(command);
            }
            catch (NumberFormatException | NoUserConnected e) {
                scanner = new Scanner(System.in);
            }
        }
        return nextView;
    }


    public void displayNewMessage(List<Message> message) throws NoUserConnected {
        if(message.isEmpty()) System.out.println("There is no new messages :(");
        else {
            System.out.println("------------New Chats------------");
            for (Message value : message) {
                System.out.println(value);
            }
            System.out.println("---------------------------------");
        }
        controller.removeNewMessages();
        //Print new message info
    }

    public void displayNewMessageAfterSend(Message message) {
        System.out.println(message);
    }

    public void displayAllMessages(List<Message> messages) {
        if(messages.isEmpty()) System.out.println("There is no messages :(");
        else {
            System.out.println("--------------Chats--------------");
            for (Message message : messages) {
                System.out.println(message);
                if (controller.hasLinkProtection()) message.extractUrls();

            }
            System.out.println("---------------------------------");
        }
    }


    public void sendMessage() {
        System.out.println("Enter message content: ");
        String content = scanner.nextLine();
        try {
            controller.sendMessage(content);
        } catch (NoUserConnected noUserConnected) {
            noUserConnected.printStackTrace();
        }
    }



    protected List<String> getMenuOptions() {
        List<String> options = new ArrayList<>(List.of(
                "Send message",
                "See all messages",
                "Go back"
        ));

        if (controller.canResearch()) {
            options.add("Research a message");
        }

        return options;
    }



    public String enterMessageToSearch() {
        System.out.print("Enter the content of the message you want to find: ");
        return scanner.nextLine();
    }

    public void displayMessages(List<Message> messages) {
        if (messages.isEmpty()) {
            System.out.println("No messages found");
        }
        else {
            for (Message message: messages) {
                System.out.println(message);
                if (controller.hasLinkProtection()) message.extractUrls();
            }
        }
    }


    public void getNewListMessage() throws NoUserConnected, UserDoesntExist {
        List<Message> newMessages = controller.getNewListMessage();
        if(newMessages.isEmpty()){
            System.out.println("There is no new messages :(");
        }else {
            System.out.println("------------New Chats------------");
            for(Message m: newMessages){
                System.out.println(m);
            }
            System.out.println("---------------------------------");
            controller.removeAllNewMessages();
        }


    }
}
