package State.Views;

import State.Controllers.ChatController;
import State.Exceptions.NoUserConnected;
import State.Models.Message;
import State.Models.User;

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
    public AbstractView run() throws NoUserConnected {
        while (!exit) {
            displayOptions();
            int command = Integer.parseInt(scanner.nextLine());
            controller.parseInput(command);
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

    public void displayAllMessages(List<Message> message) {
        if(message.isEmpty()) System.out.println("There is no messages :(");
        else {
            System.out.println("--------------Chats--------------");
            for (Message value : message) {
                System.out.println(value);
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

    public void displayOptions() {
        if (!controller.canResearch()) {
            System.out.println("""
                    Do you want to :\s
                     (1) Send message\s
                     (2) See all messages\s
                     (3) See new messages\s
                     (4) Go back\s
                     (5) Exit\s
                     Enter the number you want to select:  """);
        }
        else {
            System.out.println("""
                    Do you want to :\s
                     (1) Send message\s
                     (2) See all messages\s
                     (3) See new messages\s
                     (4) Go back\s
                     (5) Exit\s
                     (6) Research a message\s
                     Enter the number you want to select:  """);

        }
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
            }
        }
    }
}
