package State.Models;


import State.Features.Feature;

import java.util.List;
import java.util.Map;

import State.Features.FeatureName;
import State.State;

public class User {

    private String name;
    private String password;
    private Map<FeatureName, Feature> features;

    public User(String name, String password) {
        features = State.getInstance().getFeatures();
        this.name = name;
        this.password = password;
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