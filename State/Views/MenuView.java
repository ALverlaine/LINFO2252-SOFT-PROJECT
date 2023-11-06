package State.Views;

import State.Controllers.MenuController;
import State.Features.Feature;
import State.Features.FeatureName;

import java.util.Map;
import java.util.Scanner;

public class MenuView extends AbstractView {
    private MenuController controller;

    public MenuView(AbstractView previousView) {
        super(previousView);
        this.controller = new MenuController(this);
    }

    @Override
    public AbstractView run() {
        while (!exit) {
            createOutput();
            try {
                int command = Integer.parseInt(scanner.nextLine());
                controller.parseInput(command);
            }
            catch (NumberFormatException e) {
                scanner = new Scanner(System.in);
            }
        }
        return nextView;
    }

    public void createOutput() {
        String output =
                """
                        What do you wish to do?\s
                        (1) View your chats\s
                        (2) Activate a feature\s
                        (3) Deactivate a feature\s
                        (4) Log out\s
                        (5) Exit\s
                        Enter your choice: """;
        System.out.println(output);
    }

    public void goToChats() {
        exit = true;
        nextView = new ChatListView(this);
    }

    public void logOut() {
        nextView = new AuthView(this);
        exit = true;
    }

    public int selectFeature(Map<FeatureName, Feature> features) {
        int i = 1;
        StringBuilder output = new StringBuilder();
        output.append("Here are the features to select from: \n");
        for (FeatureName name: features.keySet()) {
            String featureString = "(" + i + ") " + name.toString() + "\n";
            output.append(featureString);
            i++;
        }
        output.append("Select the feature: \n");
        System.out.println(output);
        try {
            return Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            inputNotRecognized();
            return -1;
        }
    }


    public void wrongFeatureSelected() {
        System.out.println("Wrong feature selected");
    }

}
