package Views;

import Controllers.MenuController;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.Feature;
import Features.FeatureName;

import java.util.ArrayList;
import java.util.List;
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
            displayOptions();
            try {
                int command = Integer.parseInt(scanner.nextLine());
                controller.parseInput(command);
            }
            catch (NumberFormatException | NoUserConnected | UserDoesntExist e) {
                scanner = new Scanner(System.in);
            }
        }
        return nextView;
    }


    protected List<String> getMenuOptions() {
        List<String> options = new ArrayList<>(List.of(
                "View new message",
                "Activate a feature",
                "Deactivate a feature",
                "Log out"
        ));
        if (!controller.isDriving()) options.add("View your chats");
        return options;
    }

    public void goToChats() {
        exit = true;
        nextView = new ChatListView(this);
    }

    public void logOut() {
        nextView = new AuthView(this);
        controller.logout();
        exit = true;
    }

    public int selectFeature(Map<FeatureName, Feature> features) {
        int i = 1;
        StringBuilder output = new StringBuilder();
        output.append("Here are the features to select from: \n");

        for (Map.Entry<FeatureName, Feature> entry : features.entrySet()) {
            FeatureName name = entry.getKey();
            Feature feature = entry.getValue();

            String featureString = String.format("(%d) %s - %s%n", i, name, feature.isActivated() ? "Activated" : "Deactivated");
            output.append(featureString);
            i++;
        }
        output.append("Select the feature: ");
        System.out.print(output);
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
