package CliControllers;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.FeatureName;
import Services.FeatureService;
import Utils.AppState;
import Views.ChatCLIView;
import Views.MenuCLIView;

public class CliMenuController {
    private AppState appState = AppState.getInstance();
    private MenuCLIView view;
    private ChatCLIView chatView;
    private FeatureService featureService = FeatureService.getInstance();
    public CliMenuController(MenuCLIView view) {
        this.view = view;
        this.chatView = new ChatCLIView(view);
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
