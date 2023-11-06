package State.Controllers;
import State.Features.Feature;
import State.Features.FeatureName;
import State.State;
import State.Views.MenuView;

import java.util.Map;

public class MenuController extends AbstractController {
    private State state = State.getInstance();
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
    }

    public void parseInput(int input) {
        final int CHATS = 1;
        final int ACTIVATE = 2;
        final int DEACTIVATE = 3;
        final int LOGOUT = 4;
        final int EXIT = 5;

        switch (input) {
            case CHATS -> view.goToChats();
            case ACTIVATE -> selectFeature(true);
            case DEACTIVATE -> selectFeature(false);
            case LOGOUT -> view.logOut();
            case EXIT -> System.exit(0);
        }
    }

    public void selectFeature(boolean activate) {
        Map<FeatureName, Feature> features = state.getFeatures();
        Feature feature = null;
        try {
            int intFeature = view.selectFeature(state.getFeatures());
            feature = features.get(FeatureName.fromInt(intFeature - 1));
        }
        catch (IllegalArgumentException e) {
            view.wrongFeatureSelected();
            return;
        }
        if (activate) feature.activate();
        else feature.deactivate();
    }
}
