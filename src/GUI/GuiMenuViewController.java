package GUI;

import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.Feature;
import Features.FeatureName;
import GuiControllers.GuiMenuController;
import GuiInterfaces.Views.IAppViewController;
import GuiWidget.FeatureButton;
import ViewsAbstract.IMenuView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiMenuViewController extends AbstractViewController implements Initializable, IMenuView {

    IAppViewController app;
    GuiMenuController controller;
    @FXML
    HBox features;
    public GuiMenuViewController(IAppViewController app) {
        super(app);
        this.app = app;
        this.controller = new GuiMenuController(this);
    }


    public void addFeature(FeatureName featureName, boolean isActivated) {
        FeatureButton featureButton = new FeatureButton(featureName, isActivated);
        featureButton.setClickHandler(this::changeFeatureStatus);
        features.getChildren().add(featureButton);
    }

    public void changeFeatureStatus(FeatureName featureName) {
        controller.changeFeatureState(featureName);
        resetFeatures();
    }

    public void resetFeatures() {
        features.getChildren().clear();
        controller.addFeatures();
    }

    @Override
    public void logOut() {
    }

    @FXML
    public void logout() {
        controller.logout();
        app.setTitle("SoftwareMaintenance");
        app.handleViewChange(EPages.SIGN_IN);

    }

    public void setTitle(String title) {
        app.setTitle(title);
    }
    @FXML
    public void goToChats() {
        app.handleViewChange(EPages.CHAT_LIST);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller.initialize();
    }

}
