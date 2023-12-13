package Services;

import Exceptions.NoUserConnected;
import Exceptions.UserDoesntExist;
import Features.Feature;
import Features.FeatureName;
import Features.Status;
import Models.User;
import Utils.AppState;

import java.util.Map;

public class FeatureService {
    private AppState appState = AppState.getInstance();
    private static FeatureService instance = null;

    private FeatureService () {}

    public static FeatureService getInstance() {
        if (instance == null) instance = new FeatureService();
        return instance;
    }

    public Map<FeatureName, Feature> getFeatures() {
        User connectedUser = null;
        try {
            connectedUser = appState.getConnectedUser();
        }
        catch (NoUserConnected ignored) {}
        assert connectedUser != null;
        return connectedUser.getFeatures();
    }

    public boolean featureIsActivated(FeatureName feature) {
        Map<FeatureName, Feature> features = getFeatures();
        return features.get(feature).isActivated();
    }


    public boolean researchActivated() {
        return featureIsActivated(FeatureName.Research);
    }

    public String getUserStatus(String username) {
        try {
            User user = appState.getUser(username);
            Map<FeatureName, Feature> features = user.getFeatures();
            return features.get(FeatureName.Status).toString();
        }
        catch (UserDoesntExist ignored) {
            return new Status().toString();
        }
    }

    public void changeFeatureState(int featureInt, boolean activate) {
        Feature feature = getFeatures().get(FeatureName.fromInt(featureInt - 1));
        if (activate) feature.activate();
        else feature.deactivate();
    }

    public boolean changeFeatureState(FeatureName featureName) {
        Feature feature = getFeatures().get(featureName);
        feature.changeStatus();
        return feature.isActivated();
    }

    public void logout() {
        Map<FeatureName, Feature> features = getFeatures();
        for (Feature feature: features.values()) feature.deactivate();
    }

}
