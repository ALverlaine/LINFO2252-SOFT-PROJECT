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
    public ChatView() {
        super();
        this.controller = new ChatController(this);
    }

    @Override
    public void run() {
        //Display all messages
        //Get message from view
        //Send message to controller
        displayOptions();
    }

    public void sendMessage(String content) throws NoUserConnected {
        // Get message from view
        controller.sendMessage(content);
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
    public void displayOptions() {
        System.out.println("""
                Do you want to :\s
                 (1) Send message\s
                 (2) See all messages\s
                 (3) See new messages\s
                 (4) Go back\s
                 Enter the number you want to select:  """);
        int command = Integer.parseInt(scanner.nextLine());
        switch (command){
            case 1:
                System.out.println("Enter message content: ");
                String content = scanner.nextLine();
                try {
                    sendMessage(content);
                } catch (NoUserConnected noUserConnected) {
                    noUserConnected.printStackTrace();
                }
                displayOptions();
            case 2:
                //if(controller.getMessages() != null && controller.getMessages().getMessages() != null) displayAllMessages(controller.getMessages().getMessages());
                //else System.out.println("There is no messages");
                System.out.println("From how do you want to get all the messages: ");
                List<User> UserList = controller.getChatUsers();
                for(User user: UserList) {
                    System.out.println(user.getName());
                }
                String user = scanner.nextLine();
                //displayAllMessages(controller.getChat().getMessages());
                displayOptions();
            case 3:
                //displayNewMessage(controller.getChat().getMessages().get(controller.getChat().getMessages().size()-1));
                displayOptions();
            case 4:
                ViewsController viewsController = new ViewsController();
                viewsController.displayOption();
                break;
            default:
                System.out.println("Command not found, enter again");
                displayOptions();
        }
    }


}
