package State.Services;

import State.Exceptions.NoUserConnected;
import State.Exceptions.UserDoesntExist;
import State.Features.Feature;
import State.Features.FeatureName;
import State.Features.Status;
import State.Models.User;
import State.State;

import java.util.Map;

public class FeatureService {
    private State state = State.getInstance();
    private static FeatureService instance = null;

    private FeatureService () {}

    public static FeatureService getInstance() {
        if (instance == null) instance = new FeatureService();
        return instance;
    }

    public Map<FeatureName, Feature> getFeatures() {
        User connectedUser = null;
        try {
            connectedUser = state.getConnectedUser();
        }
        catch (NoUserConnected ignored) {}
        return connectedUser.getFeatures();
    }

    public boolean featureIsActivated(FeatureName feature) {
        Map<FeatureName, Feature> features = getFeatures();
        return features.get(feature).isActivated();
    }

    public boolean statusActivated() {
        return featureIsActivated(FeatureName.Status);
    }

    public boolean researchActivated() {
        return featureIsActivated(FeatureName.Research);
    }

    public String getUserStatus(String username) {
        try {
            User user = state.getUser(username);
            Map<FeatureName, Feature> features = user.getFeatures();
            return features.get(FeatureName.Status).toString();
        }
        catch (UserDoesntExist ignored) {
            return new Status().toString();
        }
    }

}
