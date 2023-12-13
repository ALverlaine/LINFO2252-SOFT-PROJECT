package ControllersAbstract;

import Features.FeatureName;
import Models.Chat;
import Services.ChatService;
import Services.FeatureService;
import Utils.AppState;
import Views.ChatCLIView;
import Views.MenuCLIView;
import ViewsAbstract.IMenuView;

public class AbstractMenuController {
    IMenuView view;
    protected AppState appState = AppState.getInstance();
    protected FeatureService featureService = FeatureService.getInstance();

    public AbstractMenuController(IMenuView view) {
        this.view = view;
    }

    public void logout() {
        featureService.logout();
    }

    public boolean changeFeature(FeatureName featureName) {
        return featureService.changeFeatureState(featureName);
    }
}
