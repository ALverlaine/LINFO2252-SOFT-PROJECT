package Models;


import Features.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import Utils.AppState;

public class User {

    private String name;
    private String password;
    private TreeMap<FeatureName, Feature> features;

    public User(String name, String password) {
        features = AppState.getInstance().getFeatures();
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public TreeMap<FeatureName, Feature> getFeatures() {
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