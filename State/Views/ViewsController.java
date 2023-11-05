package State.Views;

import java.util.*;
import java.util.Scanner;

public class ViewsController {
    private final Map<ViewOption, AbstractView> viewMap = new HashMap<>();
    Scanner scanner = new Scanner(System.in);


    public ViewsController() {
        viewMap.put(ViewOption.Chat, new AuthView());
        //viewMap.put(ViewOption.Chat_list, new ChatListView());
        //viewMap.put(ViewOption.Contact, new ContactView());
        displayOption();
    }

    public AbstractView displayOption() {
        System.out.println("Please enter one of the following options: ");
        for(ViewOption option : ViewOption.values()) {
            System.out.println(option);
        }
        String input = scanner.nextLine();

        try{
            ViewOption viewOption = ViewOption.valueOf(input);
            AbstractView view = viewMap.get(viewOption);
            return view;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input please try again or enter exit to quit");
            return displayOption();
        }

    }
}
