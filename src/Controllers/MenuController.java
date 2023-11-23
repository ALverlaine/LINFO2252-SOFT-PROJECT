package Controllers;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.Feature;
import Features.FeatureName;
import Models.User;
import Services.FeatureService;
import Utils.AppState;
import Views.ChatView;
import Views.MenuView;

import java.util.Map;

public class MenuController extends AbstractController {
    private AppState appState = AppState.getInstance();
    private MenuView view;
    private ChatView chatView;
    private FeatureService featureService = FeatureService.getInstance();
    public MenuController(MenuView view) {
        this.view = view;
        this.chatView = new ChatView(view);
    }

    public void parseInput(int input) throws NoUserConnected, UserDoesntExist {
        final int NEW_MESSAGE = 1;
        final int ACTIVATE = 2;
        final int DEACTIVATE = 3;
        final int LOGOUT = 4;
        final int CHATS = 5;
        final int EXIT = 0;

        switch (input) {
            case CHATS -> {if (!isDriving()) view.goToChats();}
            case ACTIVATE -> selectFeature(true);
            case DEACTIVATE -> selectFeature(false);
            case LOGOUT -> view.logOut();
            case EXIT -> System.exit(0);
            case NEW_MESSAGE -> chatView.getNewListMessage();
        }
    }

    public void selectFeature(boolean activate) {
        try {
            int intFeature = view.selectFeature(featureService.getFeatures());
            featureService.changeFeatureState(intFeature, activate);
        }
        catch (IllegalArgumentException e) {
            view.wrongFeatureSelected();
        }
    }

    public boolean isDriving() {
        return featureService.featureIsActivated(FeatureName.Driving);
    }

    public void logout() {
        featureService.logout();
    }
}
