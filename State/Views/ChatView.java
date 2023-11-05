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
    public AbstractView run() {
        while (!exit) {
            displayOptions();
            int command = Integer.parseInt(scanner.nextLine());
            controller.parseInput(command);
        }
        return nextView;
    }


    public void displayNewMessage(Message message) {
        System.out.println(message);
        //Print new message info
    }

    public void displayAllMessages(List<Message> message) {
        if(message == null) System.out.println("No messages");
        else {
            for (Message value : message) {
                System.out.println(value);
            }
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
        System.out.println("""
                Do you want to :\s
                 (1) Send message\s
                 (2) See all messages\s
                 (3) See new messages\s
                 (4) Go back\s
                 Enter the number you want to select:  """);

    }
}
