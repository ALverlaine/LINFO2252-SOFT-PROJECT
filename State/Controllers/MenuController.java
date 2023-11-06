package State.Controllers;
import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;
import State.Features.Feature;
import State.Features.FeatureName;
import State.State;
import State.Views.ChatView;
import State.Views.MenuView;

import java.util.Map;

public class MenuController extends AbstractController {
    private State state = State.getInstance();
    private MenuView view;
    private ChatView chatView;

    public MenuController(MenuView view) {
        this.view = view;
        this.chatView = new ChatView(view);
    }

    public void parseInput(int input) throws NoUserConnected, UserDoesntExist {
        final int CHATS = 1;
        final int NEW_MESSAGE = 2;
        final int ACTIVATE = 3;
        final int DEACTIVATE = 4;
        final int LOGOUT = 5;
        final int EXIT = 6;



        switch (input) {
            case CHATS -> view.goToChats();
            case ACTIVATE -> selectFeature(true);
            case DEACTIVATE -> selectFeature(false);
            case LOGOUT -> view.logOut();
            case EXIT -> System.exit(0);
            case NEW_MESSAGE -> chatView.getNewListMessage();
        }
    }

    public void selectFeature(boolean activate) {
        Map<FeatureName, Feature> features = state.getFeatures();
        Feature feature = null;
        try {
            int intFeature = view.selectFeature(state.getFeatures());
            feature = features.get(FeatureName.fromInt(intFeature - 1));
            //System.out.println(feature);
        }
        catch (IllegalArgumentException e) {
            view.wrongFeatureSelected();
            return;
        }
        if (activate) feature.activate();
        else feature.deactivate();
    }
}
