package Features;

public abstract class Feature {
    private boolean activated = false;
    public Feature() {}

    public void changeStatus() {
        activated = !activated;
    }
    public void activate() {
        activated = true;
    }

    public void deactivate() {
        activated = false;
    }

    public abstract void logout();

    public boolean isActivated() {
        return activated;
    }
}
