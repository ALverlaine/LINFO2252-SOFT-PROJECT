package Features;

public class Driving extends Feature {

    public Driving() {}

    @Override
    public void logout() {
        deactivate();
    }
}
