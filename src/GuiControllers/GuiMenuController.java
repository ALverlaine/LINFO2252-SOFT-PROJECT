package GuiControllers;

import ControllersAbstract.AbstractMenuController;
import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.Feature;
import Features.FeatureName;
import GUI.GuiMenuViewController;
import GuiInterfaces.Controllers.IController;
import GuiInterfaces.Views.IViewController;
import GuiWidget.FeatureButton;
import Models.Message;

import java.util.List;
import java.util.Map;

public class GuiMenuController extends AbstractMenuController implements IController {
    GuiMenuViewController view;

    public GuiMenuController(GuiMenuViewController view) {
        super(view);
        this.view = view;
    }

    @Override
    public void initialize() {
        for (Map.Entry<FeatureName, Feature> entry: featureService.getFeatures().entrySet()) {
            FeatureName name = entry.getKey();
            Feature feature = entry.getValue();
            view.addFeature(name, feature.isActivated());
        }
        setWindowTitle();

    }

    public boolean changeFeatureState(FeatureName featureName) {
        boolean newStatus = super.changeFeature(featureName);
        if (featureName == FeatureName.Status || featureName == FeatureName.DND) setWindowTitle();
        return newStatus;

    }


    public void setWindowTitle() {
        try {
            if (featureService.featureIsActivated(FeatureName.Status))
                if (featureService.featureIsActivated(FeatureName.DND))
                    view.setTitle(appState.getConnectedUserName() + " - Do not Disturb");
                else
                    view.setTitle(appState.getConnectedUserName() + " - Online");
            else
                view.setTitle(appState.getConnectedUserName());
        }
        catch (NoUserConnected e) {}
    }



    public void addFeatures() {
        for (Map.Entry<FeatureName, Feature> entry: featureService.getFeatures().entrySet()) {
            FeatureName name = entry.getKey();
            Feature feature = entry.getValue();
            view.addFeature(name, feature.isActivated());
        }

    }

}
