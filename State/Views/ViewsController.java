package State.Views;

import State.Exceptions.NoUserConnected;

import java.util.*;
import java.util.Scanner;

public class ViewsController {
    private final Map<ViewOption, AbstractView> viewMap = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public ViewsController() {
      //  viewMap.put(ViewOption.CHAT, new ChatView());
        //viewMap.put(ViewOption.Chat_list, new ChatListView());
        //viewMap.put(ViewOption.Contact, new ContactView());
        //displayOption();
    }

    public void displayOption() throws NoUserConnected {
        System.out.println("Please enter one of the following options: ");
        for(ViewOption option : ViewOption.values()) {
            System.out.println(option);
        }
        String input = scanner.nextLine().toUpperCase();

        try{
            if(input.equals("EXIT")){
                System.out.println("Exiting...");
                System.exit(0);
            }
            ViewOption viewOption = ViewOption.valueOf(input);
            AbstractView view = viewMap.get(viewOption);
            view.run();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input please try again or enter exit to quit");
            displayOption();
        }
    }

    public void run() throws NoUserConnected {
        AbstractView tmp = new AuthView(null);
        AbstractView view = new AuthView(tmp);
        while (true) {
            view = view.run();
        }
    }
}
