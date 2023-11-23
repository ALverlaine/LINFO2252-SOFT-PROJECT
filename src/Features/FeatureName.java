package Features;

public enum FeatureName {
    Research,
    Status,
    Driving,
    Link_Protection;

    public static FeatureName fromInt(int value) {
        for (FeatureName feature : FeatureName.values()) {
            if (feature.ordinal() == value) {
                return feature;
            }
        }
        throw new IllegalArgumentException("Invalid int value for FeatureName: " + value);
    }
}
