package State.Models;


import State.Features.Feature;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import State.Features.FeatureName;
import State.Features.Research;
import State.Features.Status;
import State.State;

public class User {

    private String name;
    private String password;
    private Map<FeatureName, Feature> features = new LinkedHashMap<>();

    public User(String name, String password) {
        features = State.getInstance().getFeatures();
        this.name = name;
        this.password = password;

        features.put(FeatureName.Status, new Status());
        features.put(FeatureName.Research, new Research());
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Map<FeatureName, Feature> getFeatures() {
        return features;
    }

    @Override
    public String toString () {
        return "Username: " + name + "\n";
    }

    public String asMessageString() {
        return "From: " + name + " ";
    }
}